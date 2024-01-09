package kr.co.sloop.postAssignment.repository;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.mapper.PostAssignmentMapper;
import kr.co.sloop.postForum.domain.PostForumDTO;
import kr.co.sloop.postForum.mapper.PostForumMapper;
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
}
