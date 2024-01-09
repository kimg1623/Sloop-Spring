package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostAssignmentRepository {
    // 글 목록 조회
    List<PostAssignmentDTO> list(SearchDTO searchDTO);
    // 전체 글 개수 조회
    int searchAndCountPostsByBoardIdx(SearchDTO searchDTO);
}
