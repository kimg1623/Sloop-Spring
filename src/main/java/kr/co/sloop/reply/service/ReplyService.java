package kr.co.sloop.reply.service;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void saveReply(ReplyDTO replyDTO) {
        replyRepository.saveReply(replyDTO);
    }

    public void updateReply(ReplyDTO replyDTO) {
        replyRepository.updateReply(replyDTO);
    }

    public void deleteReply(ReplyDTO replyDTO) {
        replyRepository.deleteReply(replyDTO);
    }

    public List<ReplyDTO> getRepliesByPostId(int postIdx) {
        return replyRepository.findAll(postIdx);
    }


}