package kr.co.sloop.reply.service;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void save(ReplyDTO replyDTO) {
        replyRepository.save(replyDTO);
    }

    public void update(ReplyDTO replyDTO) {
        replyRepository.update(replyDTO);
    }

    public void deleteReply(int replyIdx) {
        replyRepository.deleteReply(replyIdx);
    }

    public List<ReplyDTO> findAll(int postIdx) {
        return replyRepository.findAll(postIdx);
    }
}