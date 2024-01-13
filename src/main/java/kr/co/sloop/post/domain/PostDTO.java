package kr.co.sloop.post.domain;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class PostDTO {
    protected int postIdx; // 게시글 idx
    protected int boardIdx; // 게시판 idx
}
