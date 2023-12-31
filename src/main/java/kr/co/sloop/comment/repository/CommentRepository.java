package kr.co.sloop.comment.repository;

import kr.co.sloop.comment.domain.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final SqlSessionTemplate sql;

    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save", commentDTO);
    }

    public void update(CommentDTO commentDTO) {
        sql.update("Comment.update", commentDTO);
    }

    public void delete(Long commentId) {
        sql.delete("Comment.delete", commentId);
    }

    public CommentDTO findById(Long commentId) {
        return sql.selectOne("Comment.findById", commentId);
    }

    public List<CommentDTO> findAll(Long boardId) {
        return sql.selectList("Comment.findAll", boardId);
    }

    public List<CommentDTO> findPagedComments(Long boardId, int offset, int limit) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("boardId", boardId);
        parameters.put("offset", offset);
        parameters.put("limit", limit);
        return sql.selectList("Comment.findPagedComments", parameters);
    }
}