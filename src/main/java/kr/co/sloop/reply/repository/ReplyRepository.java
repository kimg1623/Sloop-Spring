package kr.co.sloop.reply.repository;

import kr.co.sloop.reply.domain.ReplyDTO;

import java.util.List;

public interface ReplyRepository {
    void save(ReplyDTO replyDTO);
    void update(ReplyDTO replyDTO);
    void deleteReply(int replyIdx);
    List<ReplyDTO> findAll(int postIdx);
}