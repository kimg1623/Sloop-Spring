package kr.co.sloop.postAssignment.service;

import kr.co.sloop.post.domain.PageDTO;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.repository.PostAssignmentRepository;
import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostAssignmentServiceImpl implements PostAssignmentService{
    private final PostAssignmentRepository postAssignmentRepository;

    // 글 목록 조회
    @Override
    public ArrayList<PostAssignmentDTO> list(SearchDTO searchDTO) {
        searchDTO.setOffset((searchDTO.getPage() - 1) * PageDTO.postsPerPage); // // (현재 페이지 - 1) * 페이지 당 글 개수

        List<PostAssignmentDTO> postAssignmentDTOList = postAssignmentRepository.list(searchDTO);

        // List -> ArrayList
        ArrayList<PostAssignmentDTO> postForumDTOArrayList = new ArrayList<>(postAssignmentDTOList);
        return postForumDTOArrayList;
    }
}
