package kr.co.sloop.post.service;

import kr.co.sloop.post.domain.SearchDTO;

public interface SearchService {
    // 게시판에 맞는 전체 페이지 수, 시작 페이지 번호, 마지막 페이지 번호를 계산하여 SearchDTO 인스턴스에 담아 반환한다.
     /* Params :
    - page : 현재 페이지
    - boardIdx : 게시판 Idx
    - searchType : 검색 유형
    - keyword : 검색어
    - boardType : 게시판 유형. 1(공지), 2(과제), 3(자유), 4(공부인증)
     */
    SearchDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType);
}
