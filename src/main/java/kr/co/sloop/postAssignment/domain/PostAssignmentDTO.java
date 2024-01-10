package kr.co.sloop.postAssignment.domain;

import kr.co.sloop.post.domain.PostDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

// 과제 게시판의 게시글
@Getter
@Setter
@ToString
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
}
