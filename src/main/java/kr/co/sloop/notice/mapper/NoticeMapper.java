package kr.co.sloop.notice.mapper;

import kr.co.sloop.notice.domain.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface NoticeMapper {
  List<NoticeDTO> findAllNoticeList(Model model);
  int insertPost(NoticeDTO noticeDTO);
  int insertNoticeWrite(NoticeDTO noticeDTO);
}