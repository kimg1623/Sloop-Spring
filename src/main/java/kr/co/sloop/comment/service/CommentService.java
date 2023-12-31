package kr.co.sloop.comment.service;

import kr.co.sloop.comment.domain.CommentDTO;
import kr.co.sloop.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }

    public void update(CommentDTO commentDTO) {
        commentRepository.update(commentDTO);
    }

    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }

    public CommentDTO findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }

    public List<CommentDTO> findPagedComments(Long boardId, int offset, int limit) {
        return commentRepository.findPagedComments(boardId, offset, limit);
    }
}