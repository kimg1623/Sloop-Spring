package kr.co.sloop.notice.repository;


import kr.co.sloop.notice.domain.NoticeDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface NoticeRepository {

  List<NoticeDTO> findAllNoticeList(Model model);


  int insertPost(NoticeDTO noticeDTO);

  int insertNoticeWrite(NoticeDTO noticeDTO);
}
