package kr.co.sloop.notice.repository.impl;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.mapper.NoticeMapper;
import kr.co.sloop.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class NoticeRepositoryImpl implements NoticeRepository {
  private final NoticeMapper noticeMapper;

  @Override
  public List<NoticeDTO> findAllNoticeList(Model model) {
    return noticeMapper.findAllNoticeList(model);
  }

  @Override
  public int insertPost(NoticeDTO noticeDTO) {
    return noticeMapper.insertPost(noticeDTO);
  }

  @Override
  public int insertNoticeWrite(NoticeDTO noticeDTO) {
    return noticeMapper.insertNoticeWrite(noticeDTO);
  }


}
