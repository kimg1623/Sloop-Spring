package kr.co.sloop.study.controller;

import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.service.StudyGroupService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

		model.addAttribute("categoryGradeList1", null);
		return "study/addStudyGroup";
	}

	@PostMapping("/add")
	@ResponseBody
	public String submitAddStudyGroupForm(@ModelAttribute("StudyGroupDTO") StudyGroupDTO studyGroupDTO){
		studyGroupDTO.setStudyGroupCode(getRandomStudyGroupCode());
		log.info("StudyGroupDTO=====>"+studyGroupDTO);
		int result = studyGroupService.insertNewStudyGroup(studyGroupDTO);
		if(result == 1)
			return "redirect:/study/"+studyGroupDTO.getStudyGroupCode(); // 스터디 그룹의 메인 페이지
		else
			return "study/list";
	}

	@RequestMapping(value = "/{studyGroupCode}", method = RequestMethod.GET)
	public String requestStudyGroup(@PathVariable("studyGroupCode") String studyGroupCode, Model model){
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(studyGroupCode);
		model.addAttribute("studyGroup", studyGroupDTO);
		return "study/home";
	}

	@RequestMapping(value = "/{studyGroupCode}/manage", method = RequestMethod.GET)
	public String requestStudyGroupSetting(@PathVariable("studyGroupCode") String studyGroupCode, Model model){
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(studyGroupCode);
		model.addAttribute("studyGroup", studyGroupDTO);
		return "study/manageStudyGroup";
	}



	// 카테고리 ajax 불러오기
//	@RequestMapping(value = "/{option1}", produces = "application/json; charset=UTF-8", method= RequestMethod.GET)
//	@ResponseBody
//	public void get_option1(HttpServletResponse res, @PathVariable String option1) throws IOException {
//
//		List<Option> options = studyGroupService.findOption1(option1);
//		List<String> optionList = new ArrayList();
//
//		for (int i = 0; i < options.size(); i++) {
//			optionList.add(options.get(i).getOption1());
//		}
//
//		JSONArray jsonArray = new JSONArray();
//		for (int i = 0; i < optionList.size(); i++) {
//			jsonArray.put(optionList.get(i));
//		}
//
//		PrintWriter pw = res.getWriter();
//		pw.print(jsonArray.toString());
//		pw.flush();
//		pw.close();
//	}
	public static JSONObject convertMapToJson(HashMap<String,Object> map){
        
        JSONObject json = new JSONObject();
        for(Map.Entry<String, Object>entry:map.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            json.put(key, value);
        }
        return json;
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
