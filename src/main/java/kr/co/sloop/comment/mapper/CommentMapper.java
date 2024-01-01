package kr.co.sloop.comment.mapper;

import kr.co.sloop.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    void saveComment(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);

    void deleteComment(CommentDTO commentDTO);

    List<CommentDTO> findAll(int postIdx);

}