package kr.co.sloop.notice.controller;


import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/notice")
	public String noticeList(Model model){

		List<NoticeDTO> noticeList	= noticeService.findAllNoticeList(model);
		model.addAttribute("noticeList" , noticeList);
		return "notice/noticeList";
	}

}