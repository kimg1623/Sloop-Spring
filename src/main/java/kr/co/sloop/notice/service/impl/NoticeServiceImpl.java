package kr.co.sloop.notice.service.impl;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.repository.NoticeRepository;
import kr.co.sloop.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeServiceImpl implements NoticeService {
  private final NoticeRepository noticeRepository;


  @Override
  public List<NoticeDTO> findAllNoticeList(Model model) {
    return noticeRepository.findAllNoticeList(model);
  }


}