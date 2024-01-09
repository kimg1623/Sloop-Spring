package kr.co.sloop.notice.service;

import kr.co.sloop.notice.domain.NoticeDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface NoticeService {
  List<NoticeDTO> findAllNoticeList(Model model);


}