package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.postAssignment.domain.AssignmentDTO;
import kr.co.sloop.postAssignment.mapper.AssignmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AssignmentRepositoryImpl implements AssignmentRepository{
    private final AssignmentMapper assignmentMapper;

    @Override
    public int insertAssignment(AssignmentDTO assignmentDTO) {
        return assignmentMapper.insertAssignment(assignmentDTO);
    }
}
