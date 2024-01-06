package kr.co.sloop.postForum.service;

import kr.co.sloop.post.domain.PageDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import kr.co.sloop.postForum.repository.PostForumRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostForumServiceImpl implements PostForumService {
    private final PostForumRepositoryImpl postForumRepositoryImpl;

    // 글 작성
    @Override
    public boolean write(PostForumDTO postForumDTO) {
        int result = 0;
        // memberEmail로 memberIdx 알아내기
        Integer memberIdx = postForumRepositoryImpl.selectMemberIdxByMemberEmail(postForumDTO.getMemberEmail());
        if(memberIdx == null)   return false; // memberEmail에 해당하는 memberIdx가 존재하지 않는다.
        postForumDTO.setMemberIdx(memberIdx.intValue());

        // post 글 작성
        result = postForumRepositoryImpl.insertPost(postForumDTO);
        if(result != 1) return false;   // 글 작성 실패

        // postForum 글 작성
        result = postForumRepositoryImpl.insertPostForum(postForumDTO);
        if (result != 1) return false;  // 글 작성 실패

        // 글 작성 성공
        return true;
    }

    // 글 목록 조회
    @Override
    public ArrayList<PostForumDTO> list(int boardIdx, int page) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("boardIdx", boardIdx);
        map.put("limit", PageDTO.postsPerPage); // 페이지 당 글 개수
        map.put("offset", (page - 1) * PageDTO.postsPerPage); // (현재 페이지 - 1) * 페이지 당 글 개수
        List<PostForumDTO> postForumDTOList = postForumRepositoryImpl.list(map);

        // List -> ArrayList
        ArrayList<PostForumDTO> postForumDTOArrayList = new ArrayList<>(postForumDTOList);
        return postForumDTOArrayList;
    }

    // postIdx로 글 정보 불러오기
    @Override
    public PostForumDTO findByPostIdx(int postIdx) {
        PostForumDTO postForumDTO = postForumRepositoryImpl.findByPostIdx(postIdx);
        return postForumDTO;
    }

    // 글 수정
    @Override
    public boolean update(PostForumDTO postForumDTO) {
        int result = postForumRepositoryImpl.update(postForumDTO);

        if(result == 1){    // 성공
            return true;
        }else{  // 실패
            return false;
        }
    }

    // 글 삭제
    @Override
    public void delete(int postIdx) {
        int result = postForumRepositoryImpl.delete(postIdx);
    }

    // 글 상세 조회
    @Override
    public PostForumDTO detailForm(int postIdx) {
        // 조회수 증가
        postForumRepositoryImpl.updatePostForumHits(postIdx);

        // postIdx로 글 정보 불러오기
        return findByPostIdx(postIdx);
    }
}
