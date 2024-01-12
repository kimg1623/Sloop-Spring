package kr.co.sloop.notice.controller;


import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.domain.NoticeSearchDTO;
import kr.co.sloop.notice.service.NoticeService;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.post.service.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

	private final NoticeService noticeService;

	/** 페이징과 검색도 함께 만든다. */

	@GetMapping("/list")
	public String noticeList(@RequestParam(
											value = "page", 		// 'page' 라는 이름의 http 요청 파라미터를 읽어온다
											required = false , 	 	// 파라미터가 필수는 아니라는 뜻
											defaultValue = "1") 	// 파라미터가 없거나 비어있을 경우 기본값은 1로 설정한다.
								 			int page , 				// 그러고나서 page 변수에 값을 대입. 아래는 비슷한 주석이라 생략한다.
							 @RequestParam(value = "searchType" , defaultValue = "0" , required = false) int searchType,
							 @RequestParam(value = "keyword" , defaultValue = "" , required = false) String keyword,
							 Model model){

		// 게시판 idx
		int boardIdx = 1;

		// 검색어 앞뒤 공백 제거
		keyword = keyword.trim();

		// 검색 + 페이징을 위한 객체
		NoticeSearchDTO noticeSearchDTO = noticeService.initialize(boardIdx , page , searchType , keyword, 1);
		model.addAttribute("noticeSearchDTO" , noticeSearchDTO);

		// 글 목록 조회 + 검색 + 페이징
		ArrayList<NoticeDTO> noticeList	= noticeService.findAllNoticeList(noticeSearchDTO);
		/* JSP 에 noticeList 라는 변수명으로 넘겨준다. */
		model.addAttribute("noticeList" , noticeList);


		return "notice/list";
	}

	// 공지사항 작성 폼 출력
	@GetMapping("/write")
	public String noticeWriteForm(Model model){

		NoticeDTO noticeDTO = new NoticeDTO();
		model.addAttribute("noticeDTO", noticeDTO);
		return "notice/noticeWriteForm";
	}

	/** 공지사항 작성은
	 * 1) 로그인이 되었는지(추후 권한부여해서 조건 추가 해야됨)
	 * 2) 로그인된 사용자의 memberIdx 는 몇인지 세션에 의해 가져오고
	 * 3) 게시판 은 공지게시판 ( 1 )에 작성 */
	@PostMapping("/write")
	public String noticeWrite(@Valid @ModelAttribute("noticeDTO") NoticeDTO noticeDTO , BindingResult errors , HttpSession session){

		if (errors.hasErrors()){
			return "notice/noticeWriteForm";
		}

		/** 세션에서 이메일 , memberIdx 값 불러옴 */
		String memberEmail = (String)session.getAttribute("loginEmail");
		int memberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
		/** 추후에 PathVariable 값과 동일하게 세팅
		 * 현재는 편의상 1로 direct로 설정 후 집어넣음*/
		int boardIdx = 1;
		// noticeDTO 에 해당 변수값을 set 해준다.
		noticeDTO.setMemberEmail(memberEmail);
		noticeDTO.setBoardIdx(boardIdx);
		noticeDTO.setMemberIdx(memberIdx);
		// 서비스단의 로직을 수행하고 값을 넘겨받음.
		boolean result = noticeService.noticeWrite(noticeDTO);

		if (result){
			return "redirect:/notice/list";
		} else {
			return "member/loginForm";
		}
	}

	/** 공지 게시글 상세 조회 폼 */
	@GetMapping("detail")
	public String detail(@RequestParam("postIdx") int postIdx , Model model){


		NoticeDTO noticeDTO = noticeService.detailNotice(postIdx);

		model.addAttribute("noticeDTO" , noticeDTO);
		/** 본인(관리자) 게시물 */

		/** 누구나 볼 수 있는 게시물 */

		return "notice/detail";

	}

	@GetMapping("update")
	public String updateNoticeForm(@RequestParam("postIdx") int postIdx , HttpSession session , Model model ){

		String getNickname = (String) session.getAttribute("loginMemberNickname");

		if (getNickname != null) {
			NoticeDTO noticeDTO = noticeService.findByPostIdx(postIdx);
			model.addAttribute("noticeDTO" , noticeDTO);
			return "notice/updateForm";
		} else {
			return "member/loginForm";
		}
	}

	@PostMapping("update")
	public String updateNotice(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO ,
														 HttpSession session){
		// 로그인된 회원과 글 작성자가 동일한지 검사


		if (!noticeDTO.getMemberEmail().equals(session.getAttribute("loginEmail"))){
			// 동일하지 않다면 리스트로 리다이렉트
			return "redirect:/notice/list";
		}
			// 글 수정하기
		boolean result = noticeService.updateNotice(noticeDTO);
		log.info("==========DTOOOOOO" + noticeDTO);
		if (result){ // 수정 성공

			return "redirect:/notice/detail?postIdx=" + noticeDTO.getPostIdx();
		} else { // 수정 실패
			return "redirect:/notice/list";
		}
	}


}