package kr.co.sloop.post.repository;

import kr.co.sloop.post.domain.PostDTO;
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
  
    // post 테이블에 글 작성하기
    @Override
    public int insertPost(PostDTO postDTO) {
        return postMapper.insertPost(postDTO);
    }

    // post 테이블에서 글 삭제하기
    @Override
    public int delete(int postIdx) {
        return postMapper.delete(postIdx);
    }

    // memberEmail로 memberIdx 찾기
    @Override
    public int selectMemberIdxByMemberEmail(String memberEmail) {
        return postMapper.selectMemberIdxByMemberEmail(memberEmail);
    }
}
