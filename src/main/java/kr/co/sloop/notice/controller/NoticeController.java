package kr.co.sloop.notice.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sloop.notice.Paging;
import kr.co.sloop.notice.domain.NoticeDTO;
import kr.co.sloop.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;

import javax.servlet.http.*;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	//페이징 유틸을 이용한공지사항 목록 페이지 이동
	@GetMapping("/list")
	public ModelAndView list(NoticeDTO noticeDTO) throws Exception {

		log.info("Controller @GetMapping(/list) 페이징 공지사항 목록 화면이동 >>>>>>>>>>>>>>> ");
		log.info("공지사항 검색 >>>>>>>>>>>>>>>>>>>>>> noticeDTO : "+noticeDTO);

		// 데이터와 뷰를 동시에 설정이 가능
		ModelAndView mv = new ModelAndView();

		//페이징 유틸 선언
		Paging paging = new Paging();

		if(noticeDTO.getPage() > 1) {
			noticeDTO.setStartIndex((noticeDTO.getPage() - 1 ) * 10);
		}

		int totalCnt = noticeService.selectNoticeCnt(noticeDTO);

		//get 파라미터 url 셋팅
		String paramUrl =	"/notice/list?searchType="+chkStringNull(noticeDTO.getSearchType())+"&searchTxt="+chkStringNull(noticeDTO.getSearchTxt());

		//jsp 화면 맵핑
		mv.setViewName("notice/list");
		//리스트 객체 셋팅
		mv.addObject("list", noticeService.selectListNoticePaging(noticeDTO));
		//총 갯수 셋팅
		mv.addObject("totalCnt", totalCnt);
		//페이징 태그 셋팅
		mv.addObject("pagingHtml", paging.getPagingElement(totalCnt, noticeDTO.getPage(), noticeDTO.getRow(), paramUrl));

		return mv;
	}

	//공지사항 쓰기/수정 페이지 이동
	@GetMapping("/write")
	public String write(NoticeDTO noticeDTO, Model m, HttpServletResponse response, HttpSession session) throws Exception {


		//로그인정보가 없으면 로그인 페이지로 이동
		/*
    	if(session.getAttribute("loginMemberInfo") == null) {
			return "login/login";
		}
		*/

		log.info("Controller @GetMapping(/write) 공지사항쓰기/수정 화면이동 >>>>>>>>>>>>>>> ");
		//String writer = (String)session.getAttribute("id");

		if(noticeDTO.getPostIdx() != null) {
			log.info("Controller @GetMapping(/write) 화면에서 넘어온 Dto의 값 : "+noticeDTO.toString());
			m.addAttribute("noticeDTO", noticeService.selectDetailNotice(noticeDTO));
		}

		return "notice/write";
	}

	//공지사항 상세조회 이동
	@GetMapping("/detail")
	public String detail(Model m, NoticeDTO noticeDTO, HttpServletResponse response, HttpSession session) throws Exception {
		log.info("Controller @GetMapping(/detail) 공지사항상세화면 이동 >>>>>>>>>>>>>>> ");
		//String writer = (String)session.getAttribute("id");

		m.addAttribute("noticeDTO",noticeService.selectDetailNotice(noticeDTO));

		return "notice/detail";
	}

	//공지사항 저장 (insert문 호출)
	@PostMapping("/insertNotice")
	public void insertNotice(NoticeDTO noticeDTO, HttpServletResponse response, HttpSession session) {

		log.info("Controller @PostMapping(/insertNotice) 화면에서 넘어온 Dto의 값 : "+noticeDTO.toString());

		noticeDTO.setMemberIdx(1);

		//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			out = response.getWriter();
			result = noticeService.insertNotice(noticeDTO);
			//마이바티스에서 insert문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out.println("<script>alert('게시글을 작성하였습니다.');  location.href='/notice/list';</script>");
				out.flush();
			}else {
				out.println("<script>alert('작성에 실패하였습니다.');  location.href='/notice/write';</script>");
				out.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//공지사항 삭제 (delete문 호출)
	@PostMapping("/deleteNotice")
	public void deleteNotice(NoticeDTO noticeDTO, HttpServletResponse response, HttpSession session) {

		log.info("Controller @PostMapping(/deleteNotice) 화면에서 넘어온 Dto의 값 : "+noticeDTO.toString());

		//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			out = response.getWriter();
			result = noticeService.deleteNotice(noticeDTO);
			//마이바티스에서 delete문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out.println("<script>alert('게시글을 삭제하였습니다.');  location.href='/notice/list';</script>");
				out.flush();
			}else {
				out.println("<script>alert('삭제를 실패하였습니다.');  location.href='/notice/detail';</script>");
				out.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//공지사항 수정 (update문 호출)
	@PostMapping("/updateNotice")
	public void updateNotice(NoticeDTO noticeDTO, HttpServletResponse response, HttpSession session) {

		log.info("Controller @PostMapping(/updateNotice) 화면에서 넘어온 Dto의 값 : "+noticeDTO.toString());

		//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			result = noticeService.updateNotice(noticeDTO);
			//마이바티스에서 update문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out = response.getWriter();
				out.println("<script>alert('게시글을 수정하였습니다.');  location.href='/notice/list';</script>");
				out.flush();
			}else {
				out = response.getWriter();
				out.println("<script>alert('수정에 실패하였습니다.');  location.href='/notice/write';</script>");
				out.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String chkStringNull (String str) {
		String result = "";
		if(str == "null" || str == null) {
			result = "";
		}else {
			result = str;
		}
		return result;
	}
}