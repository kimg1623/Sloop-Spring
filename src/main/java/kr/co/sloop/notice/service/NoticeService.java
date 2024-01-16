package kr.co.sloop.notice.service;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;
import kr.co.sloop.post.domain.SearchDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public interface NoticeService {
  ArrayList<NoticeDTO> findAllNoticeList(NoticeSearchDTO noticeSearchDTO);
  NoticeDTO detailNotice(int postIdx);
  boolean noticeWrite(NoticeDTO noticeDTO);
  NoticeDTO findByPostIdx(int postIdx);
  boolean updateNotice(NoticeDTO noticeDTO);
  NoticeSearchDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType);
}