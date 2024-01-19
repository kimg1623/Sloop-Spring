package kr.co.sloop.daily.service.impl;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;

import java.util.ArrayList;
import java.util.List;

public interface DailyService {
    //공부인증 전체리스트 불러오기
    //페이징
    ArrayList<DailyDTO> pageList(PageDTO pageDTO);

    //검색+페이징 객체
    PageDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType);

    //공부인증 게시글 작성
    boolean dailyWrite(DailyDTO dailyDTO, AttachmentDTO attachmentDTO);

    //상세보기
    DailyDTO findByPostIdx(int postIdx);

    //수정하기
    int update(DailyDTO dailyDTO);

    //삭제
    void delete(int postIdx);

    //조회수
    void updateViewCnt(int postIdx);

}
