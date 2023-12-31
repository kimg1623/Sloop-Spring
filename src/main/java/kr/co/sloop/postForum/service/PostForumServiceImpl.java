package kr.co.sloop.postForum.service;

import kr.co.sloop.postForum.domain.PostForumDTO;
import kr.co.sloop.postForum.repository.PostForumRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
