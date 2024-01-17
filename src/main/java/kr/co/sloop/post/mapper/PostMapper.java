package kr.co.sloop.post.mapper;

import kr.co.sloop.post.domain.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int countAllPostsByBoardIdx(int boardIdx);
    // 글 작성하기
    int insertPost(PostDTO postDTO);
    // 글 삭제하기
    int delete(int postIdx);

    int selectMemberIdxByMemberEmail(String memberEmail);
}
