package kr.co.sloop.notice.mapper;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface NoticeMapper {
  int insertPost(NoticeDTO noticeDTO);
  int insertNoticeWrite(NoticeDTO noticeDTO);
  NoticeDTO findByPostIdx(int postIdx);
  int updateNotice(NoticeDTO noticeDTO);
  void updateNoticeHits(int postIdx);
  ArrayList<NoticeDTO> selectAllNoticeByBoardIdx(NoticeSearchDTO noticeSearchDTO);
  int searchAndCountPostsByBoardIdx(NoticeSearchDTO boardIdx);

  String deletePostByPostIdx(int postIdx);

  String findMemberEmailByPostIdx(int postIdx);
}