package kr.co.sloop.postAssignment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AssignmentDTO {
    private int assignmentIdx; // 과제 idx
    private Timestamp assignmentBeginDate;  // 시작일시
    private Timestamp assignmentEndDate;    // 마감일시
    private Integer assignmentScore;        // 만점 점수

    public AssignmentDTO(Timestamp assignmentEndDate, Integer assignmentScore){
        this.assignmentEndDate = assignmentEndDate;
        this.assignmentScore = assignmentScore;
    }
}
