package kr.co.sloop.post.domain;

import lombok.*;

// 검색 + 페이징을 위한 객체
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO{
    private int page; // 현재 페이지
    private int maxPage; // 전체 페이지
    private int beginningPage; // 현재 페이지 기준 시작 페이지
    private int endingPage; // 현재 페이지 기준 마지막 페이지
    private int offset; // sql limit-offset

    static public int postsPerPage = 10; // 1 페이지 당 글의 개수는 10
    static public int pageLimit = 5; // 한 페이지에 보여지는 페이지는 5개. 즉, << < 12345 > >>

    private int boardIdx; // 게시판 idx
    private int searchType; // 검색 유형
    private String keyword; // 검색어

    // 생성자
    public SearchDTO(int boardIdx, int page, int searchType, String keyword){
        this.boardIdx = boardIdx;
        this.page = page;
        this.searchType = searchType;
        this.keyword = keyword;
    }
}
