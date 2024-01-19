package kr.co.sloop.study.controller;

import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.service.StudyGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

	/*
	 * 메인페이지에 보여줄 모든 스터디 그룹에 대한 정보 요청 처리
	 */
	@RequestMapping // 메서드 수준의 @RequestMapping -> method 속성 기본값=GET
	public String requestStudyGroupList(Model model){ // 웹 요청 처리할 메서드
		List<StudyGroupDTO> list = studyGroupService.getAllStudyGroupList();
		model.addAttribute("studyGroupList", list); // model 객체에 view에 전달할 정보 담는다.
		return "study/list"; // view
	}

	/*
	 * LMS페이지 접근을 위한 임시 페이지
	 */
	@GetMapping("/tmp")
	public String requestStudyGroupListTmp(Model model){ // 웹 요청 처리할 메서드
		List<StudyGroupDTO> list = studyGroupService.getAllStudyGroupList();
		model.addAttribute("studyGroupList", list); // model 객체에 view에 전달할 정보 담는다.
		return "study/listtmp"; // view
	}

	/*
	 * 스터디 그룹에 대한 소개 확인하기
	 */
	/** 창규 추가 */
	@GetMapping("/introduce")
	public String requestStudyGroupIntroduce(@RequestParam("group") String groupCode, Model model){ // 웹 요청 처리할 메서드
		studyGroupService.updateStudyGroupHits(groupCode);
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(groupCode);
		int groupIdx = studyGroupDTO.getStudyGroupIdx();
		if (groupIdx > 0){
			model.addAttribute("studyGroup", studyGroupDTO); // model 객체에 view에 전달할 정보 담는다.
			return "study/introduce"; // view
		} else {
			return "study/listtmp"; // view
		}
	}
	/** 수정 전 */
	/*@GetMapping("/introduce")
	public String requestStudyGroupIntroduce(@RequestParam("group") String groupCode, Model model){ // 웹 요청 처리할 메서드
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(groupCode);
		model.addAttribute("studyGroup", studyGroupDTO); // model 객체에 view에 전달할 정보 담는다.
		return "study/introduce"; // view
	}*/
	/** 수정 전 */

	/*
	 * 스터디 그룹 개설 : 화면 처리
	 */
	@GetMapping("/add")
	public String requestAddStudyGroupForm(@ModelAttribute("StudyGroupDTO") StudyGroupDTO studyGroupDTO, Model model){
		return "study/addStudyGroup";
	}
	@PutMapping("/add")
	public ResponseEntity<?> requestAddStudyGroupForm(){
		List<CategoryRegionDTO> categoryRegionList = studyGroupService.getCategoryRegion2();
		log.info(categoryRegionList);
//		model.addAttribute("categoryRegionList", JSONArray.fromObject(categoryRegionList));
		JSONArray jsonArray = new JSONArray(categoryRegionList);
		log.info(jsonArray);
		return ResponseEntity.ok(categoryRegionList);
//		return "study/addStudyGroup";
	}

	/*
	 * 스터디 그룹 개설 : DB insert
	 */
	@PostMapping("/add")
	public String submitAddStudyGroupForm(@ModelAttribute("StudyGroupDTO") StudyGroupDTO studyGroupDTO, HttpSession session){
		studyGroupDTO.setStudyGroupCode(getRandomStudyGroupCode());
		String memberIdx = (String)session.getAttribute("loginMemberIdx");
		log.info("memberIdx=====>"+memberIdx);
		boolean result = studyGroupService.insertNewStudyGroup(studyGroupDTO, memberIdx); // insert into studyGroup + make 4 Boards + grant ROLE_STUDY_LEADER
		if(result == true)
			return "redirect:/study/"+studyGroupDTO.getStudyGroupCode(); // 스터디 그룹의 메인 페이지
		else
			return "study/list";
	}

	/**
	 * 스터디 그룹 Code로 세부정보 요청 처리
	 * URI : /study/{studyGroupCode}
	 */
	@GetMapping("/{studyGroupCode}")
	public String requestStudyGroup(@PathVariable("studyGroupCode") String studyGroupCode, Model model){
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(studyGroupCode);
		List<HashMap<String,String>> groupBoardIdxs = studyGroupService.getBoardIdxsByGroupCode(studyGroupCode);
		model.addAttribute("studyGroup", studyGroupDTO);
		model.addAttribute("groupBoardIdxs", groupBoardIdxs);
		return "study/home";
	}

	/**
	 * 스터디 그룹 관리 페이지: 스터디 설정 호출
	 * URI : /study/{studyGroupCode}/manage/info
	 */
	@GetMapping("/{studyGroupCode}/manage/info")
	public String requestStudyGroupInfo(@PathVariable("studyGroupCode") String studyGroupCode, Model model){
		StudyGroupDTO studyGroupDTO = studyGroupService.getStudyGroupByGroupCode(studyGroupCode);
		model.addAttribute("StudyGroup", studyGroupDTO);
		return "study/infoForm";
	}

	/**
	 * 스터디 설정 수정
	 * URI : /study/{studyGroupCode}/manage/info
	 */
	@PostMapping("/{studyGroupCode}/manage/info")
	public String submitUpdateStudyGroupForm(@PathVariable("studyGroupCode") String studyGroupCode,
											 @ModelAttribute("StudyGroup") StudyGroupDTO studyGroupDTO,
											 RedirectAttributes RA){
		int result = studyGroupService.updateStudyGroup(studyGroupDTO);
		if(result == 1) {
			RA.addFlashAttribute("resultMessage","수정을 완료했습니다."); // redirect로 넘길경우 model로 attribute를 넘기면 자동으로 쿼리로 변경되어 넘어감
			return "redirect:/study/" + studyGroupDTO.getStudyGroupCode() + "/manage/info"; // 그룹 정보 수정 뒤에 동일한 페이지로 redirect
		}
		else {
			log.info("스터디 그룹 정보 수정 오류");
			return "redirect:/study/" + studyGroupDTO.getStudyGroupCode() + "/manage/info"; // alert 띄울 수 있도록 경고 전송
		}
	}

	/**
	 * 스터디 구성원 관리 페이지
	 * URI : /study/{studyGroupCode}/manage/members
	 */
	@GetMapping("/{studyGroupCode}/manage/members")
	public String requestStudyGroupMembers(@PathVariable("studyGroupCode") String studyGroupCode,
											 HttpSession session, HttpServletResponse response,
											 Model model) throws IOException {
		String studyMemRole = studyGroupService.getStudyMemRoleByMemberIdx((String)session.getAttribute("loginMemberIdx"), studyGroupCode);
		if(studyMemRole.equals("ROLE_STUDY_LEADER")){
			List<Map<String, String>> studyMembers = studyGroupService.getStudyGroupMembers(studyGroupCode);
			log.info(studyMembers);
			model.addAttribute("studyMembers",studyMembers);
			return "study/members";
		} else {
			AlertUtils.alertAndBackPage(response, "스터디 리더만 접근할 수 있습니다.");
			return "redirect:/study/"+studyGroupCode;
		}
	}

	/**
	 * 스터디 구성원 관리 페이지
	 * URI : /study/{studyGroupCode}/manage/members/role
	 */
	@PostMapping("/{studyGroupCode}/manage/members/role")
	public ResponseEntity<?> requestChangeStudyGroupMemberRole(@PathVariable("studyGroupCode") String studyGroupCode,
										   HttpSession session, HttpServletResponse response,
											@RequestBody HashMap<String, Object> map,
										   	Model model) throws IOException {
		String memberIdx = (String)map.get("memberIdx");
		String studyGroupIdx = (String)map.get("studyGroupIdx");

		String studyMemRole = studyGroupService.getStudyMemRoleByMemberIdx((String)session.getAttribute("loginMemberIdx"), studyGroupCode);
		log.info("변경 상태222"+studyMemRole);
		if(studyMemRole.equals("ROLE_STUDY_LEADER")){
			int result = studyGroupService.updateStudyMemberRoleApprove(memberIdx, studyGroupIdx);
			log.info("변경 상태"+result);
			if (result == 1){
				return ResponseEntity.ok(result);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한 변경 실패 status");

			}
		} else {
			AlertUtils.alertAndBackPage(response, "스터디 리더만 접근할 수 있습니다.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("스터디 리더만 접근할 수 있습니다.");
		}
	}

	/**
	 * 스터디 그룹 폐쇄: 스터디 설정 호출
	 * URI : /study/{studyGroupCode}/delete
	 */
	@GetMapping("/{studyGroupCode}/delete")
	public String requestDeleteStudyGroup(@PathVariable("studyGroupCode") String studyGroupCode, Model model){
		// 스터디 리더일 때만 폐쇄 가능하도록 로그인한 세션과 비교할 수 있는 코드 필요
		int result = studyGroupService.deleteGroupByGroupCode(studyGroupCode);
		if(result > 0)
			return "redirect:/study"; // 나중에 동작 확인한 뒤에 redirect URI 변경 예정
		else
			return "study/"+studyGroupCode+"/infoForm";
	}

	/*
	 * 스터디 그룹 가입 신청
	 * URI: /study/join
	 */
	@PostMapping(value = "/join")
	@ResponseBody
	public Map joinStudyGroup(@RequestParam String studyGroupIdx, @RequestParam int memberIdx) {
		Map result = new HashMap<String, Object>();
		log.info("가입 신청 : " + studyGroupIdx + ", " + memberIdx);
		// studyGroupIdx 스터디 그룹에 memberIdx 회원이 가입 신청
		boolean joinResult = studyGroupService.joinStudyGroup(studyGroupIdx, memberIdx);

		if(true){	// 성공
			result.put("result", "ok");
		}else{	// 실패
			result.put("result", "false");
		}
		return result;
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
	


	/*
	 * 스터디 그룹 코드 난수 생성 메서드
	 *  usage: 스터디 그룹 개설시 사용
	 */
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
