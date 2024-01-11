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

  @Override
  public boolean noticeWrite(NoticeDTO noticeDTO) {
    int noticeWriteResult = 0;
    // 1. Post 글 등록
    noticeWriteResult = noticeRepository.insertPost(noticeDTO);
    if (noticeWriteResult != 1) return false; // 글 등록 실패

    // 2. Notice 글 작성
    noticeWriteResult = noticeRepository.insertNoticeWrite(noticeDTO);
    if (noticeWriteResult != 1) return false; // 글 작성 실패

    // 글 작성 성공
    return true;

  }

  @Override
  public NoticeDTO detailNotice(int postIdx) {

    noticeRepository.updateNoticeHits(postIdx);
    return findByPostIdx(postIdx);

  }

  public NoticeDTO findByPostIdx(int postIdx){
    NoticeDTO noticeDTO = noticeRepository.findByPostIdx(postIdx);
    return noticeDTO;
  }


  @Override
  public boolean updateNotice(NoticeDTO noticeDTO) {
    int result = noticeRepository.updateNotice(noticeDTO);
    if (result == 1){
      return true;
    } else {
      return false;
    }
  }


}