package kr.co.sloop.post.mapper;

import kr.co.sloop.post.domain.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int countAllPostsByBoardIdx(int boardIdx);

    int insertPost(PostDTO postDTO);
}
