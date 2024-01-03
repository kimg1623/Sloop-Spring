package kr.co.sloop.daily.controller;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.service.impl.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@Log4j2
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyController {

	@Autowired
	private DailyService dailyService;

	//공부 인증게시판 첫화면-전체 리스트
	@GetMapping("")
	public String getDailyList(Model model){
		List<DailyDTO> dailylist = dailyService.getAllDailyList();
		model.addAttribute("dailyList", dailylist);
		//공부인증 첫화면 jsp
		return "daily/dailyList";
	}
	//ModelAndView 사용
	@GetMapping("/all")
	public ModelAndView getAllDailyList(){
		ModelAndView modelAndView = new ModelAndView();
		List<DailyDTO> dailylist = dailyService.getAllDailyList();
		modelAndView.addObject("dailyList",dailylist);
		modelAndView.setViewName("daily/dailyList"); //daily/dailyList.jsp 파일 출력
		return modelAndView;
	}

	//공부인증 게시글 작성
	@GetMapping("/write")
	public String dailyWriteForm(){
		return "daily/dailyWrite";
	}

	//공부인증 게시글 작성
	@PostMapping("/write")
	public String dailyWrite(@ModelAttribute DailyDTO dailyDTO){

		//임의 설정
		int boardIdx = 1;
		dailyDTO.setBoardIdx(1);
		int memberIdx = 1;
		dailyDTO.setMemberIdx(1);

		int dailyWriteResult = dailyService.dailyWrite(dailyDTO);

		if(dailyWriteResult>0){
			//정상적으로 처리
			///글 작성했을 때 페이지 목록으로 가도록 수정
			return "redirect:/daily";
		}else{
			return "daily/dailyWrite";
		}
	}

	//제목으로 글 검색
	@GetMapping("/{postDailyTitle}")
	public String getDailyListByTitle(@PathVariable("postDailyTitle") String postDailyTitle, Model model){
		List<DailyDTO> dailyListByTitle = dailyService.getDailyListByTitle(postDailyTitle);
		model.addAttribute("dailyList", dailyListByTitle);
		return "daily/dailyList";
	}

	//수정
	@GetMapping("/update")
	public String updateForm(@RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily", dailyDTO);

		return "daily/dailyUpdate";
	}

	//수정
	@PostMapping("/update")
	public String update(@ModelAttribute DailyDTO dailyDTO, Model model){
		dailyService.update(dailyDTO);
		//idx값 가져오기
		DailyDTO dto = dailyService.findByPostIdx(dailyDTO.getPostIdx());
		model.addAttribute("daily",dailyDTO);
		return "daily/dailyDetail";
		//아래와 같이 redirect 을 해버리면 수정인데도 조회수 증가가 같이 됨
		//        return "redirect:/board?id="+boardDTO.getId();
	}


	//리스트 상세 보기
	@GetMapping("/detail")
	public String findByPostIdx(@RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily",dailyDTO);


		//댓글 리스트 불러오기 자리

	log.info(dailyDTO);
		return "daily/dailyDetail";
	}

	//삭제
	@GetMapping("/delete")
	public String delete(@RequestParam("postIdx") int postIdx){
		dailyService.delete(postIdx);
		return "redirect:/daily/";
	}

}
