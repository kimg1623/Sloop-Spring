package kr.co.sloop.notice.repository;


import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;

import java.util.ArrayList;

public interface NoticeRepository {

  ArrayList<NoticeDTO> findAllNoticeList(NoticeSearchDTO noticeSearchDTO);
  int insertPost(NoticeDTO noticeDTO);
  int insertNoticeWrite(NoticeDTO noticeDTO);
  NoticeDTO findByPostIdx(int postIdx);
  int updateNotice(NoticeDTO noticeDTO);
  void updateNoticeHits(int postIdx);
  int searchAndCountPostsByBoardIdx(NoticeSearchDTO boardIdx);
}
