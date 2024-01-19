package kr.co.sloop.daily.service;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.attachment.repository.AttachmentRepository;
import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;
import kr.co.sloop.daily.repository.DailyRepositoryImpl;
import kr.co.sloop.daily.service.impl.DailyService;
import kr.co.sloop.post.repository.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DailyServiceImpl implements DailyService {

    private final DailyRepositoryImpl dailyRepository;
    private final PostRepositoryImpl postRepository;
    private final AttachmentRepository attachmentRepository;

    // 공부인증 전체리스트 불러오기


    // 공부인증 게시글 작성
    @Override
    public boolean dailyWrite(DailyDTO dailyDTO, AttachmentDTO attachmentDTO) {
        int result = 0;

        /*
        임의, 수정해야함
        dailyDTO.setMemberIdx(1);
       */

        // memberEmail로 memberIdx 알아내기
        Integer memberIdx = postRepository.selectMemberIdxByMemberEmail(dailyDTO.getMemberEmail());
        if(memberIdx == null)   return false; // memberEmail에 해당하는 memberIdx가 존재하지 않는다.
        dailyDTO.setMemberIdx(memberIdx.intValue());

        // post 에 작성
        result = dailyRepository.insertPost(dailyDTO);
        if(result != 1) return false;

        // daily 글 작성
        result = dailyRepository.dailyWrite(dailyDTO);
        if (result != 1) return false;

        // 첨부파일 insert
        if(attachmentDTO != null) {
            attachmentDTO.setPostIdx(dailyDTO.getPostIdx());
            log.info("첨부파일 insert : " + attachmentDTO);
            result = dailyRepository.insertAttachment(attachmentDTO);
            if (result != 1) return false;
        }

        return true;
    }


    //상세보기
    @Override
    public DailyDTO findByPostIdx(int postIdx) {
        // 게시글
        DailyDTO dailyByPostIdx = dailyRepository.findByPostIdx(postIdx);

        // 첨부파일
        List<AttachmentDTO> attachmentDTO = attachmentRepository.findAttachmentByPostIdx(postIdx);
        if(attachmentDTO != null && !attachmentDTO.isEmpty()) {
            // 하나의 첨부파일만 관리
            dailyByPostIdx.setAttachmentDirPath(attachmentDTO.get(0).getAttachmentDirPath());
            dailyByPostIdx.setAttachmentName(attachmentDTO.get(0).getAttachmentName());
        }
        return dailyByPostIdx;
    }

    //수정하기
    @Override
    public int update(DailyDTO dailyDTO) {
        return  dailyRepository.update(dailyDTO);
    }

    //삭제
    @Override
    public void delete(int postIdx) {
        dailyRepository.delete(postIdx);
    }

    //조회수
    @Override
    public void updateViewCnt(int postIdx) {
        dailyRepository.updateViewCnt(postIdx);
    }

    //페이징
    @Override
    public ArrayList<DailyDTO> pageList(PageDTO pageDTO) {

        pageDTO.setOffset((pageDTO.getPage()-1)*PageDTO.postsPerPage);

        List<DailyDTO> pagingList = dailyRepository.pageList(pageDTO);
        log.info("[pagingList] : "+pagingList);

        ArrayList<DailyDTO> pagingDailyList = new ArrayList<>(pagingList);

        return pagingDailyList;

    }

    //검색+페이징 객체
    @Override
    public PageDTO initialize(int boardIdx, int page, int searchType, String keyword, int boardType) {

        // 게시판 idx, 현재 페이지, 검색 유형, 검색어 초기화
        PageDTO pageDTO = new PageDTO(boardIdx, page, searchType, keyword);
        //인증 : 4번
//        pageDTO.setBoardIdx(4);
        boardType=4;

        log.info("-----------------------");
        log.info("pageDTO"+pageDTO);
        log.info("boardIdx33333333 ***** : " + boardIdx);
        log.info("-----------------------");

        // 전체 글 개수
        int numOfPosts = 0;
        // 게시판에 저장된 전체 글 개수를 구한다.
        switch (boardType){
            // 공지
            case 1:
                // 공지게 시판의 전체 게시글 수를 조회
                break;
            // 과제
            case 2:
                // 과제 게시판의 전체 게시글 수를 조회
                break;
            // 자유
            case 3:
                // 자유 게시판의 전체 게시글 수를 조회
                break;
            // 공부인증
            case 4:
                // 공부인증 게시판의 전체 게시글 수initialize를 조회
                numOfPosts = dailyRepository.boardCountByBoardIdx(pageDTO);
                log.info("boardIdx값 찾기"+pageDTO);

                break;
        }

        // 전체 페이지 수 = (전체 글 개수 / 1 페이지 당 글 개수)의 올림
        int maxPage = (int)Math.ceil((double)numOfPosts / pageDTO.postsPerPage);
        pageDTO.setMaxPage(maxPage);

        // 시작 페이지 = ((현재 페이지/페이징 개수)의 올림 - 1) * 페이징 개수 + 1
        int startPage = ((int)(Math.ceil((double)page / pageDTO.pageLimit)) - 1) * pageDTO.pageLimit + 1;
        pageDTO.setStartPage(startPage);

        // 마지막 페이지
        int endPage = startPage + pageDTO.postsPerPage - 1;
        if(endPage > maxPage){
            endPage = maxPage;
        }
        pageDTO.setEndPage(endPage);

        return pageDTO;

    }
}
