package kr.co.sloop.postAssignment.mapper;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostAssignmentMapper {
    // 글 목록 조회
    List<PostAssignmentDTO> selectAllPostForumByBoardIdx(SearchDTO searchDTO);
    // 전체 글 개수 조회
    int searchAndCountPostsByBoardIdx(SearchDTO searchDTO);
}
