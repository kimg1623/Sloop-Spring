package kr.co.sloop.postForum.service;

import kr.co.sloop.postForum.domain.PostForumDTO;

public interface PostForumService {
    public boolean write(PostForumDTO postForumDTO);
}
