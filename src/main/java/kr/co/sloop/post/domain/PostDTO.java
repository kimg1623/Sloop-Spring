package kr.co.sloop.post.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDTO {
    private int postIdx; // 게시글 idx
    private int boardIdx; // 게시판 idx
}
