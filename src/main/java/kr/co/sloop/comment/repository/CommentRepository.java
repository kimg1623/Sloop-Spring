package kr.co.sloop.comment.repository;

import kr.co.sloop.comment.domain.CommentDTO;
import kr.co.sloop.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentRepository(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public void saveComment(CommentDTO commentDTO) {
        commentMapper.saveComment(commentDTO);
    }

    public void updateComment(CommentDTO commentDTO) {
        commentMapper.updateComment(commentDTO);
    }

    public void deleteComment(CommentDTO commentDTO) {
        commentMapper.deleteComment(commentDTO);
    }

    public List<CommentDTO> findAll(int postIdx) {
        return commentMapper.findAll(postIdx);
    }

}
