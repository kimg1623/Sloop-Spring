package kr.co.sloop.postForum.repository;

import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostForumRepositoryImpl implements PostForumRepository{
    private final SqlSessionTemplate sql;

    // postForum 테이블에 글 등록
    @Override
    public int insertPostForum(PostForumDTO postForumDTO) {
        int result = sql.insert("PostForum.insertPostForum", postForumDTO);
        return result;
    }

    // post 테이블에 글 등록
    @Override
    public int insertPost(PostForumDTO postForumDTO){
        int result = sql.insert("PostForum.insertPost", postForumDTO);
        // 수행 후, postForumDTO의 postIdx 멤버변수 값이 초기화됨.
        return result;
    }

    @Override
    public int selectMemberIdxByMemberEmail(String memberEmail) {
        Integer memberIdx = sql.selectOne("PostForum.selectMemberIdxByMemberEmail", memberEmail);
        return memberIdx;
    }
}
