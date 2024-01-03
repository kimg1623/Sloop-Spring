package kr.co.sloop.reply.mapper;

import kr.co.sloop.reply.domain.ReplyDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReplyMapper {

    void save(ReplyDTO replyDTO);

    void update(ReplyDTO replyDTO);

    void deleteReply(ReplyDTO replyDTO);

    List<ReplyDTO> findAll(int postIdx);

}