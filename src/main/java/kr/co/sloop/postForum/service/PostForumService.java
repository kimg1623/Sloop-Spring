package kr.co.sloop.postForum.service;

import kr.co.sloop.postForum.domain.PostForumDTO;

import java.util.ArrayList;

public interface PostForumService {
    public boolean write(PostForumDTO postForumDTO);

    ArrayList<PostForumDTO> list(int boardIdx);

    PostForumDTO findByPostIdx(int postIdx);

    boolean update(PostForumDTO postForumDTO);

    void delete(int postIdx);

    PostForumDTO detailForm(int postIdx);
}
