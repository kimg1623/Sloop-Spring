package kr.co.sloop.postForum.repository;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postForum.domain.PostForumDTO;
import kr.co.sloop.postForum.mapper.PostForumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostForumRepositoryImpl implements PostForumRepository{
    // private final SqlSessionTemplate sql;
    private final PostForumMapper postForumMapper;

    // postForum 테이블에 글 등록
    @Override
    public int insertPostForum(PostForumDTO postForumDTO) {
        // int result = sql.insert("PostForum.insertPostForum", postForumDTO);
        int result = postForumMapper.insertPostForum(postForumDTO);
        return result;
    }

    // post 테이블에 글 등록
    @Override
    public int insertPost(PostForumDTO postForumDTO){
        // int result = sql.insert("PostForum.insertPost", postForumDTO);
        int result = postForumMapper.insertPost(postForumDTO);
        // 수행 후, postForumDTO의 postIdx 멤버변수 값이 초기화됨.
        return result;
    }

    @Override
    public int selectMemberIdxByMemberEmail(String memberEmail) {
        // Integer memberIdx = sql.selectOne("PostForum.selectMemberIdxByMemberEmail", memberEmail);
        Integer memberIdx = postForumMapper.selectMemberIdxByMemberEmail(memberEmail);
        return memberIdx;
    }

    @Override
    public List<PostForumDTO> list(SearchDTO searchDTO) {
        // return sql.selectList("PostForum.selectAllPostForumByBoardIdx", boardIdx);
        return postForumMapper.selectAllPostForumByBoardIdx(searchDTO);
    }

    @Override
    public PostForumDTO findByPostIdx(int postIdx) {
        // return sql.selectOne("PostForum.findByPostIdx", postIdx);
        return postForumMapper.findByPostIdx(postIdx);
    }

    @Override
    public int update(PostForumDTO postForumDTO) {
        //return sql.update("PostForum.update", postForumDTO);
        return postForumMapper.update(postForumDTO);
    }

    @Override
    public int delete(int postIdx) {
        // return sql.delete("PostForum.delete", postIdx);
        return postForumMapper.delete(postIdx);
    }

    @Override
    public void updatePostForumHits(int postIdx) {
        // sql.update("PostForum.updatePostForumHits", postIdx);
        postForumMapper.updatePostForumHits(postIdx);
    }

    @Override
    public int searchAndCountPostsByBoardIdx(SearchDTO boardIdx) {
        return postForumMapper.searchAndCountPostsByBoardIdx(boardIdx);
    }

    @Override
    public String findWriterEmailByPostIdx(int postIdx) {
        return postForumMapper.findWriterEmailByPostIdx(postIdx);
    }
}
