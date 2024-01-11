package kr.co.sloop.notice.controller;


import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/noticeList")
	public String noticeList(Model model, NoticeDTO noticeDTO , HttpSession session){

		/** 세션에서 이메일 , memberIdx 값 불러옴 */
		String memberEmail = (String)session.getAttribute("loginEmail");
		int memberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
		/** 추후에 PathVariable 값과 동일하게 세팅
		 * 현재는 편의상 1로 direct로 설정 후 집어넣음*/

		// noticeDTO 에 해당 변수값을 set 해준다.
		noticeDTO.setMemberEmail(memberEmail);
		noticeDTO.setMemberIdx(memberIdx);

		List<NoticeDTO> noticeList	= noticeService.findAllNoticeList(model);
		model.addAttribute("noticeList" , noticeList);
		return "notice/noticeList";
	}

	// 공지사항 작성
	@GetMapping("/write")
	public String noticeWriteForm(Model model){

		NoticeDTO noticeDTO = new NoticeDTO();
		model.addAttribute("noticeDTO", noticeDTO);
		return "notice/noticeWriteForm";
	}

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
			return "redirect:/notice/noticeList";
		} else {
			return "member/loginForm";
		}
	}


}