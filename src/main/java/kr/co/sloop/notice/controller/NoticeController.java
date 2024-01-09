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
	public String noticeList(Model model){

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

		session.getAttribute("loginEmail");



		return "notice/noticeList";

	}

}