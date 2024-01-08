package kr.co.sloop.daily.controller;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.service.impl.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@Log4j2
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyController {


	private final DailyService dailyService;

	//공부 인증게시판 첫화면-전체 리스트
	@GetMapping({"","/"})
	public String getDailyList(Model model){
		List<DailyDTO> dailylist = dailyService.getAllDailyList();
		model.addAttribute("dailyList", dailylist);
		//공부인증 첫화면 jsp
		return "daily/dailyList";
	}


	//공부인증 게시글 작성
	//게시글 작성 폼 가져오기
	@GetMapping("/write")
	public String dailyWriteForm(Model model){
//		DailyDTO dailyDTO = new DailyDTO();
//		dailyDTO.setBoardIdx(1);
//		model.addAttribute("dailyDTO",dailyDTO);
		return "daily/dailyWrite";
	}

	//공부인증 게시글 작성
	@PostMapping("/write")
	public String dailyWrite(@ModelAttribute("dailyDTO") DailyDTO dailyDTO, HttpSession session){
		log.info("----dailyWritePost-----");
		log.info(dailyDTO);

		// 로그인되어있는 email Session 가져오기
		String memberEmail = (String)session.getAttribute("loginEmail");
		// int boardIdx = (int) session.getAttribute("boardIdx");
		// 수정
		int boardIdx = 4;
		dailyDTO.setMemberEmail(memberEmail);
		dailyDTO.setBoardIdx(boardIdx);

		boolean dailyWriteResult = dailyService.dailyWrite(dailyDTO);

		if(dailyWriteResult){
			//정상적으로 처리
			///글 작성했을 때 페이지 목록으로 가도록 수정
			return "redirect:/daily";
		}else{
			//실패
			return "daily/dailyWrite";
		}
	}

	//파일업로드 (x)
	@RequestMapping(value = "/write/file")
	public String requestupload1(MultipartHttpServletRequest mtfRequest) {
		String src = mtfRequest.getParameter("src");
		MultipartFile mf = mtfRequest.getFile("file");


		String path = "C:\\gitS\\imageTest\\";

		String originFileName = mf.getOriginalFilename(); // 원본 파일 명
		long fileSize = mf.getSize(); // 파일 사이즈

		System.out.println("originFileName : " + originFileName);
		System.out.println("fileSize : " + fileSize);

		String safeFile = path + System.currentTimeMillis() + originFileName;

		try {
			mf.transferTo(new File(safeFile));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}



	//제목으로 글 검색 (O)
	@GetMapping("/search")
	public String getDailyListByTitle(@RequestParam("postDailyTitle") String postDailyTitle, Model model){

		List<DailyDTO> dailylist = dailyService.getDailyListByTitle(postDailyTitle);
		model.addAttribute("dailyList", dailylist);

		if (postDailyTitle == null || postDailyTitle.trim().isEmpty()) {
			// 검색어가 없으면 다시 리스트로
			return "redirect:/daily";
		}

		return "daily/dailyList";
	}

	//수정 (O)
	@GetMapping("/update")
	public String updateForm(@RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily", dailyDTO);

		return "daily/dailyUpdate";
	}


	@PostMapping("/update")
	public String update(@ModelAttribute DailyDTO dailyDTO){
		int dailyUpdateResult = dailyService.update(dailyDTO);
//		DailyDTO dto = dailyService.findByPostIdx(dailyDTO.getPostIdx());
//		model.addAttribute("daily",dailyDTO);
		if(dailyUpdateResult>0){
			//수정 성공
			return "redirect:/daily";  //해당되는 게시물로 수정해야함
		}else{
			//수정 실패
			return "redirect:/daily"; // 리스트
		}

	}


	//리스트 상세 보기 (O)
	@GetMapping("/detail")
	public String findByPostIdx(@RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily",dailyDTO);

		//조회수 구현(o)
		//중복으로 조회수가 늘어나지 않게해야함(수정필요)
		dailyService.updateViewCnt(postIdx);

		//댓글 리스트 불러오기 자리

		log.info(dailyDTO);
		return "daily/dailyDetail";
	}

	//삭제 (O)
	@GetMapping("/delete")
	public String delete(@RequestParam("postIdx") int postIdx){
		dailyService.delete(postIdx);
		return "redirect:/daily/";
	}



}
