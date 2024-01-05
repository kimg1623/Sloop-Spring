package kr.co.sloop.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.mapper.NoticeMapper;
import kr.co.sloop.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;

	//공지사항 갯수
	@Override
	public int selectNoticeCnt(NoticeDTO noticeDto)throws Exception {
		return noticeMapper.selectNoticeCnt(noticeDto);
	}

	//페이징 공지사항 목록조회
	@Override
	public List<NoticeDTO> selectListNoticePaging(NoticeDTO noticeDto)throws Exception{
		int startIndex = (noticeDto.getPage()-1) * noticeDto.getRow();
		noticeDto.setStartIndex(startIndex);
		return noticeMapper.selectListNoticePaging(noticeDto);
	}

	//공지사항 상세조회
	@Override
	public NoticeDTO selectDetailNotice(NoticeDTO noticeDto)throws Exception {
		noticeMapper.updatePostNoticeHits(noticeDto.getPostIdx());
		return noticeMapper.selectDetailNotice(noticeDto);
	}

	//공지사항 저장
	@Override
	public int insertNotice(NoticeDTO noticeDto)throws Exception {
		return noticeMapper.insertNotice(noticeDto);
	}

	//공지사항 삭제
	@Override
	public int deleteNotice(NoticeDTO noticeDto)throws Exception {
		return noticeMapper.deleteNotice(noticeDto);
	}

	//공지사항 수정
	@Override
	public int updateNotice(NoticeDTO noticeDto)throws Exception {
		return noticeMapper.updateNotice(noticeDto);
	}

}
