package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.postAssignment.domain.AssignmentDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository {
    // 과제 삽입
    int insertAssignment(AssignmentDTO assignmentDTO);
}
