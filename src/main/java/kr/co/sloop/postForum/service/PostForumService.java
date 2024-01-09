package kr.co.sloop.postForum.service;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PostForumService {
    boolean write(PostForumDTO postForumDTO);

    // 글 목록 조회
    ArrayList<PostForumDTO> list(SearchDTO searchDTO);

    PostForumDTO findByPostIdx(int postIdx);

    boolean update(PostForumDTO postForumDTO);

    void delete(int postIdx);

    PostForumDTO detailForm(int postIdx);

    String findWriterEmailByPostIdx(int postIdx);
}
