package kr.co.sloop.reply.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class ReplyDTO {
        private int replyIdx; // 댓글 idx
        private int postIdx; // 게시글 idx
        private String replyContents; // 내용
        private int memberIdx; // 작성자 회원 id
        private LocalDateTime replyRegDate; // 작성 일시
        private LocalDateTime replyEditDate; // 수정 일시
        private int replyGroup; // 원 댓글의 id
        private int replyGroupOrder; // 댓글의 댓글 내 순서
        private int replyGroupDepth; // 댓글 들여쓰기
}