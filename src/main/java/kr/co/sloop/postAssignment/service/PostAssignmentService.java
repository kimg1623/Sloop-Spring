package kr.co.sloop.postAssignment.service;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PostAssignmentService {
    // 글 목록 조회
    ArrayList<PostAssignmentDTO> list(SearchDTO searchDTO);
}
