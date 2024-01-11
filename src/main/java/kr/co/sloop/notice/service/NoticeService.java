package kr.co.sloop.notice.service;

import kr.co.sloop.notice.domain.NoticeDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface NoticeService {
  List<NoticeDTO> findAllNoticeList(Model model);
  NoticeDTO detailNotice(int postIdx);
  boolean noticeWrite(NoticeDTO noticeDTO);
  NoticeDTO findByPostIdx(int postIdx);
  boolean updateNotice(NoticeDTO noticeDTO);


}