package kr.co.sloop.reply.repository;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyRepository {

    private final ReplyMapper replyMapper;

    @Autowired
    public ReplyRepository(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public void saveReply(ReplyDTO replyDTO) {
        replyMapper.saveReply(replyDTO);
    }

    public void updateReply(ReplyDTO replyDTO) {
        replyMapper.updateReply(replyDTO);
    }

    public void deleteReply(ReplyDTO replyDTO) {
        replyMapper.deleteReply(replyDTO);
    }

    public List<ReplyDTO> findAll(int postIdx) {
        return replyMapper.findAll(postIdx);
    }

}
