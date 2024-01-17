package kr.co.sloop.post.service;

import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.postAssignment.repository.PostAssignmentRepository;
import kr.co.sloop.postForum.repository.PostForumRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final PostForumRepositoryImpl postForumRepositoryImpl; // 자유 게시판
    private final PostAssignmentRepository postAssignmentRepository; // 과제 게시판

    // 게시판에 맞는 전체 페이지 수, 시작 페이지 번호, 마지막 페이지 번호를 계산하여 SearchDTO 인스턴스에 담아 반환한다.
     /* Params :
    - page : 현재 페이지
    - boardIdx : 게시판 Idx
    - searchType : 검색 유형
    - keyword : 검색어
    - boardType : 게시판 유형. 1(공지), 2(과제), 3(자유), 4(공부인증)
     */
    @Override
    public SearchDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType) {
        // 게시판 idx, 현재 페이지, 검색 유형, 검색어 초기화
        SearchDTO searchDTO = new SearchDTO(boardIdx, page, searchType, keyword);

        // 전체 글 개수
        int numOfPosts = 0;
        // 게시판에 저장된 전체 글 개수를 구한다.
        switch (boardType){
            // 공지
            case 1:
                // 공지 게시판의 전체 게시글 수를 조회
                break;
            // 과제
            case 2:
                // 과제 게시판의 전체 게시글 수를 조회
                numOfPosts = postAssignmentRepository.searchAndCountPostsByBoardIdx(searchDTO);
                break;
            // 자유
            case 3:
                // 자유 게시판의 전체 게시글 수를 조회
                numOfPosts = postForumRepositoryImpl.searchAndCountPostsByBoardIdx(searchDTO);
                break;
            // 공부인증
            case 4:
                // 공부인증 게시판의 전체 게시글 수를 조회
                break;
        }

        // 전체 페이지 수 = (전체 글 개수 / 1 페이지 당 글 개수)의 올림
        int maxPage = (int)Math.ceil((double)numOfPosts / searchDTO.postsPerPage);
        searchDTO.setMaxPage(maxPage);

        // 시작 페이지 = ((현재 페이지/페이징 개수)의 올림 - 1) * 페이징 개수 + 1
        int beginningPage = ((int)(Math.ceil((double)page / searchDTO.pageLimit)) - 1) * searchDTO.pageLimit + 1;
        searchDTO.setBeginningPage(beginningPage);

        // 마지막 페이지
        int endingPage = beginningPage + searchDTO.postsPerPage - 1;
        if(endingPage > maxPage){
            endingPage = maxPage;
        }
        searchDTO.setEndingPage(endingPage);

        return searchDTO;
    }
}
