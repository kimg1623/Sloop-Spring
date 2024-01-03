package kr.co.sloop.study.controller;

import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.service.StudyGroupService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/study")
@Log4j2
public class StudyGroupController {

	@Autowired // 의존성 주입
	private StudyGroupService studyGroupService;

	@RequestMapping // 메서드 수준의 @RequestMapping -> method 속성 기본값=GET
	public String requestStudyGroupList(Model model){ // 웹 요청 처리할 메서드
		List<StudyGroupDTO> list = studyGroupService.getAllStudyGroupList();
		model.addAttribute("studyGroupList", list); // model 객체에 view에 전달할 정보 담는다.
		return "study/list"; // view
	}

	@GetMapping("/add")
	public String requestAddStudyGroupForm(@ModelAttribute("StudyGroupDTO") StudyGroupDTO studyGroupDTO, Model model){
		return "study/addStudyGroup";
	}

	@PostMapping("/add")
	public String submitAddStudyGroupForm(@ModelAttribute("StudyGroupDTO") StudyGroupDTO studyGroupDTO){
		studyGroupDTO.setStudyGroupCode(getRandomStudyGroupCode());
		log.info("StudyGroupDTO=====>"+studyGroupDTO);
		int result = studyGroupService.insertNewStudyGroup(studyGroupDTO);
		if(result == 1)
			return "redirect:/study/"+studyGroupDTO.getStudyGroupCode(); // 스터디 그룹의 메인 페이지
		else
			return "study/list";
	}

	public String getRandomStudyGroupCode(){
		Random rnd =new Random();
		StringBuffer buf =new StringBuffer();
		for (int i=0;i<10;i++) { // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

			if(rnd.nextBoolean()){
				buf.append((char)((int)(rnd.nextInt(26))+65));
			}else{
				buf.append((rnd.nextInt(10)));
			}
		}
		System.out.println("buf = " + buf);
		return buf.toString();
	}
	
}
