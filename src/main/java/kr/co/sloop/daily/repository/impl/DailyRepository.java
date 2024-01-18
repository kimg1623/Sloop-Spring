package kr.co.sloop.daily.repository.impl;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;

import java.util.List;
import java.util.Map;

public interface DailyRepository {

    // 페이징-리스트
    List<DailyDTO> pageList(PageDTO pageDTO);

    // 공부인증 글 작성
    int dailyWrite(DailyDTO dailyDTO);

    // post용
    int insertPost(DailyDTO dailyDTO);

    // 상세보기
    DailyDTO findByPostIdx(int postIdx);

    // 수정하기
    int update(DailyDTO dailyDTO);

    // 삭제하기
    void delete(int postIdx);

    // 조회수
    void updateViewCnt(int postIdx);

    // 첨부파일
    int insertAttachment(AttachmentDTO attachmentDTO);
}

