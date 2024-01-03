package kr.co.sloop.postForum.service;

import kr.co.sloop.postForum.domain.PostForumDTO;

import java.util.ArrayList;

public interface PostForumService {
    boolean write(PostForumDTO postForumDTO);

    // 글 목록 조회
    ArrayList<PostForumDTO> list(int boardIdx, int page);

    PostForumDTO findByPostIdx(int postIdx);

    boolean update(PostForumDTO postForumDTO);

    void delete(int postIdx);

    PostForumDTO detailForm(int postIdx);
}
