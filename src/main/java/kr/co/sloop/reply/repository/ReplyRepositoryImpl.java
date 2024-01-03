package kr.co.sloop.reply.repository;

import kr.co.sloop.reply.domain.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final SqlSessionTemplate sql;

    @Override
    public void save(ReplyDTO replyDTO) {
        sql.insert("Reply.save", replyDTO);
    }

    @Override
    public void update(ReplyDTO replyDTO) {
        sql.update("Reply.update", replyDTO);
    }

    @Override
    public void deleteReply(int replyIdx) {
        sql.delete("Reply.deleteReply", replyIdx);
    }

    @Override
    public List<ReplyDTO> findAll(int postIdx) {
        return sql.selectList("Reply.findAll", postIdx);
    }
}