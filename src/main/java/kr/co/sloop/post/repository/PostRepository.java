package kr.co.sloop.post.repository;

import kr.co.sloop.post.domain.PostDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {
    int countAllPostsByBoardIdx(int boardIdx);
    // post 테이블에 글 작성하기
    int insertPost(PostDTO postDTO);
    // post 테이블에서 postIdx에 해당하는 글 삭제하기
    int delete(int postIdx);

    int selectMemberIdxByMemberEmail(String memberEmail);
}
