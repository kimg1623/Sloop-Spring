package kr.co.sloop.member.controller;


import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    // signupForm.jsp 로 이동
    @GetMapping("/signup")
    public String signupForm(Model model){
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        return "member/signupForm";
    }

    // signupForm.jsp -> form method = Post 로 데이터 받아옴
    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute MemberDTO memberDTO , BindingResult errors , HttpServletResponse response) throws IOException{

        if (errors.hasErrors()){
            AlertUtils.alert(response , "회원가입에 실패하였습니다.");
            return "member/signupForm";
        }

        int signupResult = memberService.signup(memberDTO);

        if (signupResult > 0) {
            AlertUtils.alertAndMovePage(response, "회원가입 되었습니다.", "login");// update 성공시 redirect로 상세보기 화면 출력
            /*return "member/signupSuccess"; // 회원가입 성공 시 이동*/
        }
        return "member/signupForm";    // 회원가입 실패 시 이동

    }

    // 회원가입 성공 후 로그인 하러 가기 버튼 클릭시 LoginForm.jsp 로 이동
    @GetMapping("/login")
    public String loginForm(){
        return "member/loginForm";
    }


    // form method = post 로 데이터 받아옴
    // login 성공시 세션에 "loginEmail" & 게시판Idx 추가
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {

        Map<String, String> loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            log.info("로그인 성공");
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            session.setAttribute("loginMemberIdx", loginResult.get("loginMemberIdx")); // 지원 추가
            session.setAttribute("loginMemberNickname", loginResult.get("loginMemberNickname")); // 지원 추가
            return "redirect:/"; // 로그인 성공시 세션에 "loginEmail"이란 이름으로 저장 후 studyList or mypage 로  // 지원 수정
        } else {
            log.info("로그인 실패");
            return "redirect:/member/login";    // 로그인 실패시 다시 GetMapping 의 LoginForm으로 redirect
        }
    }

    // 이메일 중복확인 AJAX
    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }
    // 닉네임 중복확인 AJAX
    @PostMapping("/nickname-check")
    public @ResponseBody String nicknameCheck(@RequestParam("memberNickname") String memberNickname){
        log.info("memberNickname == "+memberNickname);
        String checkResult2 = memberService.nicknameCheck(memberNickname);
        return checkResult2;
    }
    @PostMapping("/phoneNumb-check")
    public @ResponseBody String phoneNumbCheck(@RequestParam("memberPhonenumber") String memberPhonenumber){
        log.info("memberPhonenumber == "+memberPhonenumber);
        String checkResult3 = memberService.phoneNumbCheck(memberPhonenumber);
        return checkResult3;
    }
    // 회원 목록 보기 추후에 관리자 권한으로만 갈 수 있게하기
    @GetMapping("memberList")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findMemberList(model);
        model.addAttribute("memberList", memberDTOList);
        return "member/memberList";    // 관리자 일 때 memberList 이동 가능
    }

    // update.jsp의 Form 출력
    @GetMapping("update")
    public String updateForm(Model model , HttpSession session , HttpServletResponse response,
                             @RequestParam("memberIdx") int memberIdx) throws IOException {
        // 세션에 저장된 이메일 가져오기

        String loginEmail = (String) session.getAttribute("loginEmail");    // 세션에 저장된 이메일로 정보 가져오기
        if (loginEmail != null) {

            MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
            model.addAttribute("member", memberDTO);

            return "member/update";

        } else {
            return "redirect:/member/login";
        }


    }


    // update.jsp 의 Form method = Post로 데이터 받아옴
    @PostMapping("update")
    public String update (@ModelAttribute MemberDTO memberDTO,
                          HttpServletResponse response) throws IOException{
        boolean result = memberService.update(memberDTO);

        if (result) {

            AlertUtils.alertAndMovePage(response,"수정되었습니다." ,"redirect:/member?memberIdx=" + memberDTO.getMemberIdx() );// update 성공시 redirect로 상세보기 화면 출력
        }
        return "redirect:/member/update";  // update 실패시 다시 수정할 수 있게 update.jsp로 정보 가져가면서 redirect 어케함?

    }


    // 회원 리스트에서 회원 정보 페이지로 이동 -> 관리자의 기능 ( 회원페이지 페이징도 추후 진행 )
    @GetMapping
    public String findByIdx(@RequestParam("memberIdx") int memberIdx , Model model){
        MemberDTO memberDTO = memberService.findByIdx(memberIdx);   // memberIdx 파라미터 값을 가져온 뒤 해당 domain 정보를 불러온다.
        model.addAttribute("member",memberDTO);
        return "member/mypage";
    }

    // 꼭 로그인 후 마이페이지로 이동 ( 회원의 기능 )
    @GetMapping("mypage")
    public String mypage(@ModelAttribute MemberDTO memberDTO , Model model , HttpSession session){

        String loginEmail = (String) session.getAttribute("loginEmail");    // 세션에 저장된 이메일로 정보 가져오기
        memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);

        if (loginEmail != null ){

            log.info("mypage data ........" + memberDTO);

            return "redirect:/member?memberIdx="+memberDTO.getMemberIdx();  // 세션에 저장된 아이디에 맞는 마이페이지로 이동
        } else{
            return "member/loginForm"; // 세션에 있는 아이디가 없거나 맞지 않으면 loginForm으로 이동
        }

    }
    
    // 로그아웃 버튼 누를 시
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";    // 로그아웃 페이지로 이동
    }

    // 회원 탈퇴 시
    @GetMapping("/delete")
    public String deleteByUser(@RequestParam ("memberIdx") int memberIdx ,HttpSession session ,
                               HttpServletResponse response) throws IOException{

        int getMemberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
        int deleteResult = memberService.deleteByUser(memberIdx);

        if (deleteResult > 0 ){     // 성공시
            return "home"; // 성공하면 home으로 이동
        } else {
            return "redirect:/member/mypage";
        }


    }

/** 프로필 사진 업로드 */
    @GetMapping("/profile")
    public String profileUploadForm(@ModelAttribute("member") MemberDTO memberDTO ,
                                    @RequestParam("memberIdx") int memberIdx ,
                                    HttpSession session){

        String loginMemberIdx = (String) session.getAttribute("loginMemberIdx");

        // 세션에 memberIdx 와 해당 회원 정보의 memberIdx 가 맞을 시 프로필 진입 가능
        if (memberIdx == Integer.parseInt(loginMemberIdx)) {
            memberService.findByIdx(memberIdx);
            return "member/profile";
        }
        return "member/loginForm";
    }


    @ResponseBody
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("memberProfile") List<MultipartFile> multipartFile,
                             HttpServletRequest request , HttpSession session) {
        log.info("포스트업로드!!!!!!!!");

        // 기본적으로 JSON 객체로 "{"result":"FAIL"}" 이렇게 설정한다.
        String strResult = "{ \"result\":\"FAIL\" }";
        // 절대경로 초기화 - HttpServletRequest는 보안이슈가 있기에 ServletContext 를 통해 가져오는게 더 안전하지만 일단 씀
        String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
        // 파일 경로 초기화
        String fileRoot;

        // 세션에서 memberIdx 값을 가져온다.
        int memberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
        // memberDTO 내부 정보를 idx를 통해 불러온다.
        MemberDTO memberDTO = memberService.findByIdx(memberIdx);
        log.info("업로드 객체 안에 memberDTO 정보 불러오기" + memberDTO);


        try {
            // 파일이 있을때 탄다.
            if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                log.info("파일이 있음");
                // for문을 통해 file 객체 안에 정보를 대입
                for (MultipartFile file : multipartFile) {

                    // 파일 경로 재 초기화
                    fileRoot = contextRoot + "resources/upload/";
                    System.out.println(fileRoot);
                    
                    String originalFileName = file.getOriginalFilename();    //오리지날 파일명
                    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
                    String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명
                    // targetFile은 굳이 필요 없지만 나중에 쓰기 위해 객체 만들어줌
                    File targetFile = new File(fileRoot + savedFileName);

                    // memberDTO에 savedFileName으로 저장된 파일명을 넣어줌
                    memberDTO.setMemberProfile(savedFileName);
                    // uploadProfile 메서드를 이용해서 service -> repository -> mapper 순으로 DB에 update문을 통해 저장.
                    memberService.uploadProfile(memberDTO);
                    log.info("파일 저장 =====" + savedFileName);
                    log.info("타겟 파일 객체 ====" + targetFile);


                    try {
                        // 서버에 파일 저장하기 위해 쓰는 함수.
                        // getInputStream 메서드는 multipartFile 객체에서 데이터를 읽어오기 위한 InputStream을 반환.
                        InputStream fileStream = file.getInputStream();
                        // fileStream , targetFile는 commmons 라이브러리인 FileUtils 클래스를 통해 서버에 특정 디렉토리에 저장.
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
        log.info("strResult =======" + strResult);
        return strResult;
    }
/** 프로필 사진 업로드 끝 */
}
