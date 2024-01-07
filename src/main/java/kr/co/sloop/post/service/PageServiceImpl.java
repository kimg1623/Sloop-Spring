package kr.co.sloop.post.service;

import kr.co.sloop.post.domain.PageDTO;
import kr.co.sloop.post.repository.PostRepository;
import kr.co.sloop.post.repository.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService{
    private final PostRepositoryImpl postRepositoryImpl;

    // 게시판에 맞는 전체 페이지 수, 시작 페이지 번호, 마지막 페이지 번호를 계산하여 PageDTO 인스턴스에 담아 반환한다.
     /* Params :
    - page : 현재 페이지
    - boardIdx : 게시판 Idx
     */
    @Override
    public PageDTO pagingInitialize(int page, int boardIdx) {
        PageDTO pageDTO = new PageDTO();
        // 현재 페이지
        pageDTO.setCurrentPage(page);

        // 전체 페이지 수 = (전체 글 개수 / 1 페이지 당 글 개수)의 올림
        // 게시판에 저장된 전체 글 개수를 구한다.
        int numOfPosts = postRepositoryImpl.countAllPostsByBoardIdx(boardIdx);
        int maxPage = (int)Math.ceil((double)numOfPosts / PageDTO.postsPerPage);
        pageDTO.setMaxPage(maxPage);

        // 시작 페이지 = ((현재 페이지/페이징 개수)의 올림 - 1) * 페이징 개수 + 1
        int beginningPage = ((int)(Math.ceil((double)page / PageDTO.pageLimit)) - 1) * PageDTO.pageLimit + 1;
        pageDTO.setBeginningPage(beginningPage);

        // 마지막 페이지
        int endingPage = beginningPage + PageDTO.postsPerPage - 1;
        if(endingPage > maxPage){
            endingPage = maxPage;
        }
        pageDTO.setEndingPage(endingPage);

        return pageDTO;
    }
}
