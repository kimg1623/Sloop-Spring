package kr.co.sloop.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.sloop.notice.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {

	//페이징 공지사항 목록조회
	public List<NoticeDTO> selectListNoticePaging(NoticeDTO noticeDto) throws Exception;

	//공지사항 갯수
	public int selectNoticeCnt(NoticeDTO noticeDto) throws Exception;

	//공지사항 상세조회
	public NoticeDTO selectDetailNotice(NoticeDTO noticeDto) throws Exception;

	//공지사항 저장
	public int insertNotice(NoticeDTO noticeDto) throws Exception;

	//공지사항 삭제
	public int deleteNotice(NoticeDTO noticeDto) throws Exception;

	//공지사항 수정
	public int updateNotice(NoticeDTO noticeDto) throws Exception;

	//공지사항 조회수 증가
	public void updatePostNoticeHits(Integer postIdx) throws Exception;

}
