package kr.co.sloop.postForum.repository;

import kr.co.sloop.postForum.domain.PostForumDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<PostForumDTO> list(int boardIdx) {
        return sql.selectList("PostForum.selectAllPostForumByBoardIdx", boardIdx);
    }

    @Override
    public PostForumDTO findByPostIdx(int postIdx) {
        return sql.selectOne("PostForum.findByPostIdx", postIdx);
    }

    @Override
    public int update(PostForumDTO postForumDTO) {
        return sql.update("PostForum.update", postForumDTO);
    }

    @Override
    public int delete(int postIdx) {
        return sql.delete("PostForum.delete", postIdx);
    }

    @Override
    public void updatePostForumHits(int postIdx) {
        sql.update("PostForum.updatePostForumHits", postIdx);
    }
}
