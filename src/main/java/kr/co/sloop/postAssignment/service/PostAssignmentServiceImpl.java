package kr.co.sloop.postAssignment.service;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.attachment.repository.AttachmentRepository;
import kr.co.sloop.post.domain.PageDTO;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.post.repository.PostRepository;
import kr.co.sloop.postAssignment.domain.AssignmentDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.repository.AssignmentRepository;
import kr.co.sloop.postAssignment.repository.PostAssignmentRepository;
import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostAssignmentServiceImpl implements PostAssignmentService{
    private final PostAssignmentRepository postAssignmentRepository;
    private final PostRepository postRepository;
    private final AssignmentRepository assignmentRepository;
    private final AttachmentRepository attachmentRepository;
    @Resource(name="uploadPathForAssignment")
    private String uploadPath; // 업로드된 첨부파일이 저장될 서버 경로 (디렉터리 경로)

    // 글 목록 조회
    @Override
    public ArrayList<PostAssignmentDTO> list(SearchDTO searchDTO) {
        searchDTO.setOffset((searchDTO.getPage() - 1) * PageDTO.postsPerPage); // // (현재 페이지 - 1) * 페이지 당 글 개수

        List<PostAssignmentDTO> postAssignmentDTOList = postAssignmentRepository.list(searchDTO);

        // List -> ArrayList
        ArrayList<PostAssignmentDTO> postAssignmentDTOArrayList = new ArrayList<>(postAssignmentDTOList);
        return postAssignmentDTOArrayList;
    }

    // 글 작성하기
    @Override
    public boolean write(PostAssignmentDTO postAssignmentDTO, List<MultipartFile> multipartFileList) {
        int result = 0;
        boolean uploadResult = false;

        // 과제 마감일시 문자열 -> sql.Timestamp 형식으로 변환
        Timestamp assignmentEndDate = stringToTimestamp(postAssignmentDTO.getAssignmentEndDateString());
        postAssignmentDTO.setAssignmentEndDate(assignmentEndDate);

        // 과제 생성
        AssignmentDTO assignmentDTO = new AssignmentDTO(assignmentEndDate, postAssignmentDTO.getAssignmentScore());
        result = assignmentRepository.insertAssignment(assignmentDTO);
        if(result != 1) return false; // 과제 삽입 실패

        postAssignmentDTO.setAssignmentIdx(assignmentDTO.getAssignmentIdx());

        // post 글 작성
        result = postRepository.insertPost(postAssignmentDTO);
        if(result != 1) return false;   // 글 작성 실패

        // 그룹 번호 초기화
        postAssignmentDTO.setPostAssignmentGroup(postAssignmentDTO.getPostIdx());

        // postAssignment 글 작성
        result = postAssignmentRepository.insertPostAssignment(postAssignmentDTO);
        if (result != 1) return false;  // 글 작성 실패

        // 첨부파일 list를 서버에 업로드 & postAssignmentDTO 멤버변수에 등록
        uploadResult = uploadAttachmentListToServer(postAssignmentDTO, multipartFileList);
        if(!uploadResult)   return false; // 첨부파일 업로드, 등록 실패

        // 첨부파일 list를 attachment DB table에 삽입
        uploadResult = uploadAttachmentListToDBTable(postAssignmentDTO.getAttachmentDTOList());
        if(!uploadResult)   return false; // 첨부파일 insert 실패

        // 글 작성 성공
        return true;
    }

    // 과제 마감일시 자료형 변경: 문자열 -> sql.Timestamp
    public Timestamp stringToTimestamp(String timeString){
        // 뷰 jsp 페이지에서 초는 입력받지 않으므로 초를 추가하여 반환한다.
        return Timestamp.valueOf(timeString + ":00");
    }

    // 첨부파일 리스트를 서버에 업로드
    public boolean uploadAttachmentListToServer(PostAssignmentDTO postAssignmentDTO, List<MultipartFile> multipartFileList){
        boolean status = true; // 이 메서드의 정상 수행 결과
        String fileRoot; // 업로드될 디렉터리 경로
        List<AttachmentDTO> attachmentDTOList = new ArrayList<>(); // 첨부파일 객체 리스트
        int postIdx = postAssignmentDTO.getPostIdx(); // 게시글 idx

        // 첨부파일 리스트를 서버에 저장 & AttachmentDTOList에 첨부파일 추가
        for(MultipartFile file: multipartFileList) {
            fileRoot = uploadPath + File.separator + "uploads";
            System.out.println("fileRoot" + fileRoot);

            String originalFileName = file.getOriginalFilename();	// 오리지날 파일명
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	// .을 포함한 파일 확장자
            String savedFileName = UUID.randomUUID() + extension;	// 저장될 파일 명 (uuid)

            File targetFile = new File(fileRoot + File.separator + savedFileName);
            try {
                InputStream fileStream = file.getInputStream();
                FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장

                attachmentDTOList.add(new AttachmentDTO(postIdx, originalFileName, fileRoot, savedFileName)); // AttachmentDTO 생성하여 리스트에 추가
            } catch (Exception e) {
                // 파일 삭제
                FileUtils.deleteQuietly(targetFile);	// 저장된 현재 파일 삭제
                e.printStackTrace();
                status = false; // 수행 실패
                break;
            }
        }

        // 모든 파일이 서버에 정상적으로 업로드 되었을 때
        if(status) {
            // attachmentDTOList를 postAssignmentDTO에 추가
            postAssignmentDTO.setAttachmentDTOList(attachmentDTOList);
        }

        return status;
    }

    // 첨부파일 리스트를 첨부파일 DB 테이블에 삽입 [*****]
    public boolean uploadAttachmentListToDBTable(List<AttachmentDTO> attachmentDTOList){
        int result = attachmentRepository.uploadAttachmentListToDBTable(attachmentDTOList);
        if(result == attachmentDTOList.size()){     // 모든 첨부파일 insert 성공
            return true;
        }
        return false;   // insert 실패
    }

    // 글 상세조회
    @Override
    public PostAssignmentDTO detailForm(int postIdx) {
        // 조회수 증가
        postAssignmentRepository.updatePostAssignmentHits(postIdx);

        // postIdx로 글 정보 불러오기 (+ 과제 마감일시, 과제 idx)
        PostAssignmentDTO postAssignmentDTO = findByPostIdx(postIdx);
        // postIdx의 첨부파일 불러오기
        List<AttachmentDTO> attachmentDTOList = findAttachmentByPostIdx(postIdx);
        postAssignmentDTO.setAttachmentDTOList(attachmentDTOList);

        return postAssignmentDTO;
    }

    // postIdx로 글 정보 불러오기
    @Override
    public PostAssignmentDTO findByPostIdx(int postIdx) {
        PostAssignmentDTO postAssignmentDTO = postAssignmentRepository.findByPostIdx(postIdx);
        return postAssignmentDTO;
    }

    // postIdx로 첨부파일 목록 불러오기
    public List<AttachmentDTO> findAttachmentByPostIdx(int postIdx){
        return attachmentRepository.findAttachmentByPostIdx(postIdx);
    }

    // postIdx로 작성자 email 조회
    @Override
    public String findWriterEmailByPostIdx(int postIdx) {
        return postAssignmentRepository.findWriterEmailByPostIdx(postIdx);
    }

    // 글 삭제하기
    @Override
    public boolean delete(int postIdx) {
        int result = postRepository.delete(postIdx);
        if(result == 1){    // 성공
            return true;
        }else{  // 실패
            return false;
        }
    }

    // 글 수정하기 [*****]

}
