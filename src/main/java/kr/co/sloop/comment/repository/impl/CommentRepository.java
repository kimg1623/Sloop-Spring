package kr.co.sloop.comment.repository.impl;


import kr.co.sloop.comment.domain.CommentDTO;

import java.util.List;

public interface CommentRepository {
    void save(CommentDTO commentDTO);
    void update(CommentDTO commentDTO);
    void delete(Long commentId);
    CommentDTO findById(Long commentId);
    List<CommentDTO> findAll(Long boardId);
    List<CommentDTO> findPagedComments(Long boardId, int offset, int limit);
}
