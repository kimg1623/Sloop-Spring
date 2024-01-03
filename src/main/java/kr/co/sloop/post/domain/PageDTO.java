package kr.co.sloop.post.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
    private int currentPage; // 현재 페이지
    private int maxPage; // 전체 페이지
    private int beginningPage; // 현재 페이지 기준 시작 페이지
    private int endingPage; // 현재 페이지 기준 마지막 페이지

    static public int postsPerPage = 10; // 1 페이지 당 글의 개수는 5
    static public int pageLimit = 5; // 한 페이지에 보여지는 페이지는 5개. 즉, << < 12345 > >>
}
