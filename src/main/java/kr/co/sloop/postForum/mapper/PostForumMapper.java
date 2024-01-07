package kr.co.sloop.postForum.mapper;

import kr.co.sloop.post.domain.PageDTO;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostForumMapper {
    int insertPostForum(PostForumDTO postForumDTO);
    int insertPost(PostForumDTO postForumDTO);
    Integer selectMemberIdxByMemberEmail(String memberEmail);
    List<PostForumDTO> selectAllPostForumByBoardIdx(SearchDTO searchDTO);
    PostForumDTO findByPostIdx(int postIdx);
    int update(PostForumDTO postForumDTO);
    int delete(int postIdx);
    int updatePostForumHits(int postIdx);

    int searchAndCountPostsByBoardIdx(SearchDTO boardIdx);
}
