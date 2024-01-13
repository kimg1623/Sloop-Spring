package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.mapper.PostAssignmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PostAssignmentRepositoryImpl implements PostAssignmentRepository{
    private final PostAssignmentMapper postAssignmentMapper;

    // 글 목록 조회
    @Override
    public List<PostAssignmentDTO> list(SearchDTO searchDTO) {
        return postAssignmentMapper.selectAllPostForumByBoardIdx(searchDTO);
    }

    // 전체 글 개수 조회
    @Override
    public int searchAndCountPostsByBoardIdx(SearchDTO searchDTO) {
        return postAssignmentMapper.searchAndCountPostsByBoardIdx(searchDTO);
    }

    // postAssignment 테이블에 글 작성하기
    @Override
    public int insertPostAssignment(PostAssignmentDTO postAssignmentDTO) {
        return postAssignmentMapper.insertPostAssignment(postAssignmentDTO);
    }

    // postIdx로 글 정보 불러오기
    @Override
    public PostAssignmentDTO findByPostIdx(int postIdx) {
        return postAssignmentMapper.findByPostIdx(postIdx);
    }

    // 조회수 증가
    @Override
    public void updatePostAssignmentHits(int postIdx) {
        postAssignmentMapper.updatePostAssignmentHits(postIdx);
    }
}
