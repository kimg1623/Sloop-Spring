package kr.co.sloop.daily.repository.impl;

import kr.co.sloop.daily.domain.DailyDTO;

import java.util.List;
import java.util.Map;

public interface DailyRepository {
    //공부인증 전체리스트 불러오기
    List<DailyDTO> getAllDailyList();

    //공부인증 글 작성
    int dailyWrite(DailyDTO dailyDTO);

    //post용
    int insertPost(DailyDTO dailyDTO);

    //제목으로 글 검색
    List<DailyDTO> getDailyListByTitle(String postDailyTitle);

    //상세보기
    DailyDTO findByPostIdx(int postIdx);

    //수정하기
    int update(DailyDTO dailyDTO);

    //삭제하기
    void delete(int postIdx);

    //조회수
    void updateViewCnt(int postIdx);

    //페이징
    List<DailyDTO> pageList(Map<String, Integer> pagingParams);


}

