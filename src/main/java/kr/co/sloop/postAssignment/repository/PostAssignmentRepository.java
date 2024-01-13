package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostAssignmentRepository {
    // 글 목록 조회
    List<PostAssignmentDTO> list(SearchDTO searchDTO);
    // 전체 글 개수 조회
    int searchAndCountPostsByBoardIdx(SearchDTO searchDTO);
    // postAssignment 테이블에 글 작성하기
    int insertPostAssignment(PostAssignmentDTO postAssignmentDTO);
    // postIdx로 글 정보 불러오기
    PostAssignmentDTO findByPostIdx(int postIdx);
    // 조회수 증가
    void updatePostAssignmentHits(int postIdx);
}
