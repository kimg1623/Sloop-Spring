package kr.co.sloop.postForum.repository;

import kr.co.sloop.postForum.domain.PostForumDTO;

public interface PostForumRepository {
    public int insertPostForum(PostForumDTO postForumDTO);

    // post 테이블에 글 등록
    public int insertPost(PostForumDTO postForumDTO);


    int selectMemberIdxByMemberEmail(String memberEmail);
}
