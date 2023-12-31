package kr.co.sloop.comment.service.impl;

import kr.co.sloop.comment.domain.CommentDTO;
import kr.co.sloop.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }

    @Override
    public void update(CommentDTO commentDTO) {
        commentRepository.update(commentDTO);
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }

    @Override
    public CommentDTO findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }

    @Override
    public List<CommentDTO> findPagedComments(Long boardId, int offset, int limit) {
        return commentRepository.findPagedComments(boardId, offset, limit);
    }
}