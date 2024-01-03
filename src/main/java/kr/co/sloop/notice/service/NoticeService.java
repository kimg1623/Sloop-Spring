package kr.co.sloop.notice.service;

import java.util.*;

import kr.co.sloop.notice.domain.NoticeDTO;

public interface NoticeService {

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

}