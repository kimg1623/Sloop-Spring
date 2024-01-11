package kr.co.sloop.notice.repository.impl;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;
import kr.co.sloop.notice.mapper.NoticeMapper;
import kr.co.sloop.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
@Log4j2
public class NoticeRepositoryImpl implements NoticeRepository {
  private final NoticeMapper noticeMapper;

  @Override
  public ArrayList<NoticeDTO> findAllNoticeList(NoticeSearchDTO noticeSearchDTO) {
    return noticeMapper.selectAllNoticeByBoardIdx(noticeSearchDTO);
  }

  @Override
  public int insertPost(NoticeDTO noticeDTO) {
    return noticeMapper.insertPost(noticeDTO);
  }

  @Override
  public int insertNoticeWrite(NoticeDTO noticeDTO) {
    log.info("핀 값은========="+noticeDTO.getPostNoticePinned());
    return noticeMapper.insertNoticeWrite(noticeDTO);
  }

  @Override
  public NoticeDTO findByPostIdx(int postIdx) {
    return noticeMapper.findByPostIdx(postIdx);
  }

  @Override
  public int updateNotice(NoticeDTO noticeDTO) {
    log.info("aaaaa"+noticeDTO);
    return noticeMapper.updateNotice(noticeDTO);
  }

  @Override
  public void updateNoticeHits(int postIdx) {
    noticeMapper.updateNoticeHits(postIdx);
  }

  @Override
  public int searchAndCountPostsByBoardIdx(NoticeSearchDTO boardIdx) {
    return noticeMapper.searchAndCountPostsByBoardIdx(boardIdx);
  }


}
