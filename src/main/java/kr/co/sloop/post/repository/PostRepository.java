package kr.co.sloop.post.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {
    int countAllPostsByBoardIdx(int boardIdx);
}
