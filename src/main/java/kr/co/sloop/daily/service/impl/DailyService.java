package kr.co.sloop.daily.service.impl;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;

import java.util.List;

public interface DailyService {
    //공부인증 전체리스트 불러오기
    List<DailyDTO> getAllDailyList();

    //공부인증 게시글 작성
    boolean dailyWrite(DailyDTO dailyDTO);


    //제목으로 글 검색
    List<DailyDTO> getDailyListByTitle(String postDailyTitle);

    //상세보기
    DailyDTO findByPostIdx(int postIdx);

    //수정하기
    int update(DailyDTO dailyDTO);

    //삭제
    void delete(int postIdx);

    //조회수
    void updateViewCnt(int postIdx);

    //페이징
    List<DailyDTO> pageList(int page);

    //페이징-PageDTO
    PageDTO pagingParam(int page);

}
