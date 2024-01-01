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

    public void save(CommentDTO commentDTO) {
        commentMapper.save(commentDTO);
    }

    public void update(CommentDTO commentDTO) {
        commentMapper.update(commentDTO);
    }

    public void delete(CommentDTO commentDTO) {
        commentMapper.delete(commentDTO);
    }

    public List<CommentDTO> findAll(int postIdx) {
        return commentMapper.findAll(postIdx);
    }

}
