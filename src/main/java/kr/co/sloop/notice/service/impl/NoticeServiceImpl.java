package kr.co.sloop.notice.service.impl;

import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;
import kr.co.sloop.notice.repository.NoticeRepository;
import kr.co.sloop.notice.service.NoticeService;
import kr.co.sloop.post.domain.SearchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeServiceImpl implements NoticeService {

  private final NoticeRepository noticeRepository;


  @Override
  public ArrayList<NoticeDTO> findAllNoticeList(NoticeSearchDTO noticeSearchDTO) {

    noticeSearchDTO.setOffset((noticeSearchDTO.getPage() - 1 ) * noticeSearchDTO.postsPerPage);
    List<NoticeDTO> noticeDTOList = noticeRepository.findAllNoticeList(noticeSearchDTO);

    // List -> ArrayList
    ArrayList<NoticeDTO> noticeDTOArrayList = new ArrayList<>(noticeDTOList);
    return noticeDTOArrayList;
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

  // 게시판에 맞는 전체 페이지 수, 시작 페이지 번호, 마지막 페이지 번호를 계산하여 SearchDTO 인스턴스에 담아 반환한다.
     /* Params :
    - page : 현재 페이지
    - boardIdx : 게시판 Idx
    - searchType : 검색 유형
    - keyword : 검색어
    - boardType : 게시판 유형. 1(공지), 2(과제), 3(자유), 4(공부인증)
     */
  @Override
  public NoticeSearchDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType) {

    // 게시판 idx , 현재 페이지 , 검색 유형 , 검색어 초기화 (인스턴스 생성)
    NoticeSearchDTO noticeSearchDTO = new NoticeSearchDTO(boardIdx , page , searchType , keyword);

    // 전체 글 개수
    int numOfPosts = 0;
    // 게시판에 저장된 전체글 개수를 구한다.
    switch (boardType){
      // 공지
      case 1:
          // 공지 게시판 전체 게시글 조회
        numOfPosts = noticeRepository.searchAndCountPostsByBoardIdx(noticeSearchDTO);
        break;
      case 2:
          // 과제 게시판 전체 게시글 조회
        break;
      case 3:
          // 자유 게시판 전체 게시글 조회
        break;
      case 4:
          // 공부인증 게시판 전체 게시글 조회
        break;
    }
    // 전체 페이지 수 = ( 전체 글 개수 / 1 페이지 당 글 개수) 의 올림
    int maxPage = (int)Math.ceil((double) numOfPosts / noticeSearchDTO.postsPerPage);
    noticeSearchDTO.setMaxPage(maxPage);

    // 시작 페이지 = ((현재 페이지/페이징 개수) 의 올림 -1) * 페이징 개수 + 1
    int beginningPage = ((int)(Math.ceil((double)page / noticeSearchDTO.pageLimit)) -1) * noticeSearchDTO.pageLimit + 1;
    noticeSearchDTO.setBeginningPage(beginningPage);

    // 마지막 페이지
    int endingPage = beginningPage + noticeSearchDTO.postsPerPage - 1;
    if (endingPage > maxPage){
      endingPage = maxPage;
    }
    noticeSearchDTO.setEndingPage(endingPage);

    return noticeSearchDTO;

  }

  @Override
  public String deletePostByPostIdx(int postIdx) {
    return noticeRepository.deletePostByPostIdx(postIdx);
  }

  @Override
  public String findMemberEmailByPostIdx(int postIdx) {
    return noticeRepository.findMemberEmailByPostIdx(postIdx);
  }
}