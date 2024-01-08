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
    private String postAssignmentTitle; // 제목
    private String postAssignmentContents; // 내용
    private int memberIdx; // 작성자 회원 idx
    private Timestamp postAssignmentRegDate; // 작성일시
    private Timestamp postAssignmentEditDate; // 수정일시
    private int postAssignmentHits; // 조회수

    private int assignmentIdx; // 과제 idx
    private int postAssignmentGroup;    // 그룹 번호
    private int postAssignmentGroupOrder;   // 그룹 내 순서
    private int postAssignmentGroupDepth;   // 깊이
}
