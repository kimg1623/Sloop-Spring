package kr.co.sloop.member.controller;


import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j
public class MemberController {

    private final MemberService memberService;

    // signupForm.jsp 로 이동
    @GetMapping("/signup")
    public String signupForm(){
        return "signupForm";
    }

    // signupForm.jsp -> form method = Post 로 데이터 받아옴
    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO){
        int signupResult = memberService.signup(memberDTO);

        if (signupResult > 0){
            return "signupSuccess"; // 회원가입 성공 시 이동
        } else {
            return "signupForm";    // 회원가입 실패 시 이동
        }
    }

    // 회원가입 성공 후 로그인 하러 가기 버튼 클릭시 LoginForm.jsp 로 이동
    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }


    // form method = post 로 데이터 받아옴
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {

        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            log.info("로그인 성공");
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "home"; // 로그인 성공시 세션에 "loginEmail"이란 이름으로 저장 후 studyList or mypage 로 이동
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
        String checkResult = memberService.nicknameCheck(memberNickname);
        return checkResult;
    }
    // 회원 목록 보기 추후에 관리자 권한으로만 갈 수 있게하기
    @GetMapping("memberList")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findMemberList(model);
        model.addAttribute("memberList", memberDTOList);
        return "memberList";    // 관리자 일 때 memberList 이동 가능
    }

    // update.jsp의 Form 출력
    @GetMapping("update")
    public String updateForm(Model model , HttpSession session){
        // 세션에 저장된 이메일 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");    // 세션에 저장된 이메일로 정보 가져오기
        if (loginEmail != null) {
            MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
            model.addAttribute("member", memberDTO);
            return "update";
        } else {
            return "redirect:/member/login";
        }


    }
    
    
    // update.jsp 의 Form method = Post로 데이터 받아옴
    @PostMapping("update")
    public String update (@ModelAttribute MemberDTO memberDTO){
        boolean result = memberService.update(memberDTO);
        if (result) {
            return "redirect:/member?memberIdx=" + memberDTO.getMemberIdx();    // update 성공시 redirect로 상세보기 화면 출력
        } else {
            return "redirect:/member/update";  // update 실패시 다시 수정할 수 있게 update.jsp로 정보 가져가면서 redirect 어케함?
        }
    }

    // 회원 리스트에서 회원 정보 페이지로 이동 -> 관리자의 기능 ( 회원페이지 페이징도 추후 진행 )
    @GetMapping
    public String findByIdx(@RequestParam("memberIdx") int memberIdx , Model model){
        MemberDTO memberDTO = memberService.findByIdx(memberIdx);   // memberIdx 파라미터 값을 가져온 뒤 해당 domain 정보를 불러온다.
        model.addAttribute("member",memberDTO);
        return "mypage";
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
            return "loginForm"; // 세션에 있는 아이디가 없거나 맞지 않으면 loginForm으로 이동
        }

    }
    
    // 로그아웃 버튼 누를 시
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "logout";    // 로그아웃 페이지로 이동
    }

    // 회원 탈퇴 시
    @GetMapping("/delete")
    public String deleteByUser(@RequestParam ("memberIdx") int memberIdx){
        int deleteResult = memberService.deleteByUser(memberIdx);
        if (deleteResult > 0){
            return "home"; // 성공하면 home으로 이동
        } else {
            return "redirect:/member/mypage";
        }
    }


}
