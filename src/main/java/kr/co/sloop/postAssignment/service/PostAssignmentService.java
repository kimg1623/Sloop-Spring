package kr.co.sloop.postAssignment.service;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public interface PostAssignmentService {
    // 글 목록 조회
    ArrayList<PostAssignmentDTO> list(SearchDTO searchDTO);
    // 글 작성하기
    boolean write(PostAssignmentDTO postAssignmentDTO, List<MultipartFile> multipartFileList);
    // 글 상세조회
    PostAssignmentDTO detailForm(int postIdx);
    // postIdx로 글 정보 불러오기
    PostAssignmentDTO findByPostIdx(int postIdx);
    // postIdx로 첨부파일 목록 불러오기
    List<AttachmentDTO> findAttachmentByPostIdx(int postIdx);
    // postIdx 게시글의 작성자 email 조회
    String findWriterEmailByPostIdx(int postIdx);
    // 글 삭제하기
    boolean delete(int postIdx);
}
