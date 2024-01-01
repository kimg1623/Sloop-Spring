package kr.co.sloop.comment.mapper;

import kr.co.sloop.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    void save(CommentDTO commentDTO);

    void update(CommentDTO commentDTO);

    void delete(CommentDTO commentDTO);

    List<CommentDTO> findAll(int postIdx);

}