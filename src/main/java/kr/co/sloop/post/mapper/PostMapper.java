package kr.co.sloop.post.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int countAllPostsByBoardIdx(int boardIdx);
    int selectMemberIdxByMemberEmail(String memberEmail);
}
