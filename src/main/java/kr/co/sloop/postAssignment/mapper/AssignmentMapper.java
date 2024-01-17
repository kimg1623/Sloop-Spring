package kr.co.sloop.postAssignment.mapper;

import kr.co.sloop.postAssignment.domain.AssignmentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssignmentMapper {
    int insertAssignment(AssignmentDTO assignmentDTO);
}
