package kr.co.sloop.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.co.sloop.post.mapper.PostMapper;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
    private final PostMapper postMapper;

    // 게시판의 전체 글 개수 조회
    @Override
    public int countAllPostsByBoardIdx(int boardIdx) {
        return postMapper.countAllPostsByBoardIdx(boardIdx);
    }

    // memberEmail로 memberIdx 찾기
    @Override
    public int selectMemberIdxByMemberEmail(String memberEmail) {
        return postMapper.selectMemberIdxByMemberEmail(memberEmail);
    }
}
