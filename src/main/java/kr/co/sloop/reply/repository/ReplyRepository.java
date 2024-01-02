package kr.co.sloop.reply.repository;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final SqlSessionTemplate sql;

    public void save(ReplyDTO replyDTO) {
        sql.insert("Reply.save", replyDTO);
    }

    public void update(ReplyDTO replyDTO) {
        sql.update("Reply.update", replyDTO);
    }

    public void deleteReply(int replyIdx) {
        sql.delete("Reply.deleteReply", replyIdx);
    }

    public List<ReplyDTO> findAll(int postIdx) {
        return sql.selectList("Reply.findAll", postIdx);
    }
}
