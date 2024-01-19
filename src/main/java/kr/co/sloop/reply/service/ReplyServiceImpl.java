package kr.co.sloop.reply.service;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public void save(ReplyDTO replyDTO) {
        replyRepository.save(replyDTO);
    }

    @Override
    public void update(ReplyDTO replyDTO) {
        replyRepository.update(replyDTO);
    }

    @Override
    public void deleteReply(int replyIdx) {
        replyRepository.deleteReply(replyIdx);
    }

    @Override
    public List<ReplyDTO> findAll(int postIdx) {
        return replyRepository.findAll(postIdx);
    }
}