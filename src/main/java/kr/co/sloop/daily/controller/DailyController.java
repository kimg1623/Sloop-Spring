package kr.co.sloop.daily.controller;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;
import kr.co.sloop.daily.service.impl.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@Log4j2
@RequestMapping("/study/{studyGroupCode}/daily/{boardIdx}")
@RequiredArgsConstructor
public class DailyController {

	private final DailyService dailyService;

	//	파일업로드
	@Resource(name="uploadPathforDaily")
	String uploadPath;

	//공부 인증게시판 첫화면-전체 리스트
	// 페이징 리스트(O)
	@GetMapping("/list")
	public String paging(@PathVariable("studyGroupCode") String studyGroupCode,
						 @PathVariable("boardIdx") int boardIdx,
						 @RequestParam(value = "page",required = false,defaultValue = "1") int page,
						 @RequestParam(value = "searchType", defaultValue = "0", required = false) int searchType,
						 @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
						 Model model){

		//--------------------------searchType,keyword추가--------------------------------

		// 검색어 앞뒤 공백 제거
		keyword = keyword.trim();

		//[***] 확인하기
//		int boardIdx = 4;

		// 검색+페이징
		PageDTO pageDTO = dailyService.initialize(boardIdx, page, searchType, keyword, 4);
		model.addAttribute("pageDTO",pageDTO);

		// pageDTO 확인용
		log.info("*********pageDTO:"+pageDTO);

		ArrayList<DailyDTO> pagingDailyList = dailyService.pageList(pageDTO);

		// [***] 확인필요
//		pageDTO.setBoardIdx(4);
		model.addAttribute("dailyList", pagingDailyList);
		log.info("******pagingDailyList:"+pagingDailyList);

		return "daily/dailyList";

	}


	//공부인증 게시글 작성
	//게시글 작성 폼 가져오기
	@GetMapping("/write")
	public String dailyWriteForm(@PathVariable("studyGroupCode") String studyGroupCode,
								 @PathVariable("boardIdx") int boardIdx,
								 Model model){
		return "daily/dailyWrite";
	}

	//공부인증 게시글 작성
	@PostMapping("/write")
	public String dailyWrite(@PathVariable("studyGroupCode") String studyGroupCode,
							 @PathVariable("boardIdx") int boardIdx,
							 @ModelAttribute("dailyDTO") DailyDTO dailyDTO, HttpSession session){

		log.info("[dailyWritePost,dailyDTO]:"+dailyDTO);

		// 로그인되어있는 email Session 가져오기
		String memberEmail = (String)session.getAttribute("loginEmail");

		// int boardIdx = (int) session.getAttribute("boardIdx");
//		int boardIdx = 4;

		dailyDTO.setMemberEmail(memberEmail);
		dailyDTO.setBoardIdx(boardIdx);

		boolean dailyWriteResult = dailyService.dailyWrite(dailyDTO);

		if(dailyWriteResult){
			//정상적으로 처리
			///글 작성했을 때 페이지 목록으로 가도록 수정
			return "redirect:/study/{studyGroupCode}/daily/{boardIdx}/list";
		}else{
			//실패
			return "daily/dailyWrite";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/file-upload", method = RequestMethod.POST)
	public String fileUpload(
			@PathVariable("studyGroupCode") String studyGroupCode,
			@PathVariable("boardIdx") int boardIdx,
			@RequestParam("article_file") List<MultipartFile> multipartFile
			, HttpServletRequest request) {

		String strResult = "{ \"result\":\"FAIL\" }";
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot;
		try {
			// 파일이 있을때 탄다.
			if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {

				for (MultipartFile file : multipartFile) {
					fileRoot = contextRoot + "resources/upload/";
					System.out.println(fileRoot);

					String originalFileName = file.getOriginalFilename();    //오리지날 파일명
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
					String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

					File targetFile = new File(fileRoot + savedFileName);
					try {
						InputStream fileStream = file.getInputStream();
						FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장

					} catch (Exception e) {
						//파일삭제
						FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
						e.printStackTrace();
						break;
					}
				}
				strResult = "{ \"result\":\"OK\" }";
			}
			// 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
			else
				strResult = "{ \"result\":\"OK\" }";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	//수정 (O)
	@GetMapping("/update")
	public String updateForm(@PathVariable("studyGroupCode") String studyGroupCode,
							 @PathVariable("boardIdx") int boardIdx,
							 @RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily", dailyDTO);

		return "daily/dailyUpdate";
	}

	@PostMapping("/update")
	public String update(@PathVariable("studyGroupCode") String studyGroupCode,
						 @PathVariable("boardIdx") int boardIdx,
						 @ModelAttribute DailyDTO dailyDTO){
		int dailyUpdateResult = dailyService.update(dailyDTO);
//		DailyDTO dto = dailyService.findByPostIdx(dailyDTO.getPostIdx());
//		model.addAttribute("daily",dailyDTO);
		if(dailyUpdateResult>0){
			//수정 성공
			return "redirect:/study/{studyGroupCode}/daily/{boardIdx}/list";  //해당되는 게시물로 수정해야함 [***]
		}else{
			//수정 실패
			return "redirect:/study/{studyGroupCode}/daily/{boardIdx}/list"; // 초기화면으로 이동
		}

	}


	// 게시물 상세 보기 (O)
	@GetMapping("/detail")
	public String findByPostIdx(@PathVariable("studyGroupCode") String studyGroupCode,
								@PathVariable("boardIdx") int boardIdx,
								@RequestParam("postIdx") int postIdx, Model model){
		DailyDTO dailyDTO = dailyService.findByPostIdx(postIdx);
		model.addAttribute("daily",dailyDTO);

		// 조회수 구현(o)
		// [***]중복으로 조회수가 늘어나지 않게해야함(수정필요)
		dailyService.updateViewCnt(postIdx);

		//댓글 리스트 불러오기 자리
		log.info(dailyDTO);

		return "daily/dailyDetail";
	}

	// 삭제 (O)
	@GetMapping("/delete")
	public String delete(@PathVariable("studyGroupCode") String studyGroupCode,
						 @PathVariable("boardIdx") int boardIdx,
						 @RequestParam("postIdx") int postIdx){
		dailyService.delete(postIdx);

		return "redirect:/study/{studyGroupCode}/daily/{boardIdx}/list";
	}


}
