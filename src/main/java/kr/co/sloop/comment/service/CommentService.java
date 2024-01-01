package kr.co.sloop.comment.service;

import kr.co.sloop.comment.domain.CommentDTO;
import kr.co.sloop.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentDTO commentDTO) {
        commentRepository.saveComment(commentDTO);
    }

    public void updateComment(CommentDTO commentDTO) {
        commentRepository.updateComment(commentDTO);
    }

    public void deleteComment(CommentDTO commentDTO) {
        commentRepository.deleteComment(commentDTO);
    }

    public List<CommentDTO> getCommentsByPostId(int postIdx) {
        return commentRepository.findAll(postIdx);
    }
}