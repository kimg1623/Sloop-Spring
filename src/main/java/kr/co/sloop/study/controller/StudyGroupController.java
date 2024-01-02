package kr.co.sloop.study.controller;

import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/study")
public class StudyGroupController {

	@Autowired // 의존성 주입
	private StudyGroupService studyGroupService;

	@RequestMapping // 메서드 수준의 @RequestMapping -> method 속성 기본값=GET
	public String requestBookList(Model model){ // 웹 요청 처리할 메서드
		List<StudyGroupDTO> list = studyGroupService.getAllStudyGroupList();
		model.addAttribute("studyGroupList", list); // model 객체에 view에 전달할 정보 담는다.
		return "study/list"; // view
	}
	
}
