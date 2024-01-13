package kr.co.sloop.postAssignment.domain;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.post.domain.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// 과제 게시판의 게시글
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostAssignmentDTO extends PostDTO {
    // PostDTO
    // private int postIdx; // 게시글 idx
    // private int boardIdx; // 게시판 idx

    private String postAssignmentTitle; // 제목
    private String postAssignmentContents; // 내용
    private int memberIdx; // 작성자 회원 idx
    private Timestamp postAssignmentRegDate; // 작성일시
    private Timestamp postAssignmentEditDate; // 수정일시
    private int postAssignmentHits; // 조회수

    private int assignmentIdx;              // 과제 idx
    private int postAssignmentGroup;        // 과제 그룹 번호
    private int postAssignmentGroupOrder;   // 과제 그룹 내 순서
    private int postAssignmentGroupDepth;   // 과제 깊이

    private String memberEmail; // 작성자 회원 email
    private String memberNickname; // 작성자 회원 닉네임

    private Timestamp assignmentBeginDate;  // 과제 시작일시
    private Timestamp assignmentEndDate;    // 과제 마감일시
    private String assignmentEndDateString; // 과제 마감일시 문자열 형식
    private Integer assignmentScore;    // 과제 만점 점수

    // 과제 객체 1개
    private AssignmentDTO assignment; // 과제
    // 첨부파일 list
    private List<MultipartFile> multipartFileList; // 첨부파일 여러 개
    // 첨부파일 객체 list
    private List<AttachmentDTO> attachmentDTOList; // 첨부파일 객체 여러 개


    public void addAttachmentToList(AttachmentDTO attachmentDTO){
        // list 객체 생성
        if(attachmentDTOList == null){
            attachmentDTOList = new ArrayList<>();
        }

        attachmentDTOList.add(attachmentDTO);
    }

}
