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

    @GetMapping("/signup")
    public String signupForm(){
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO){
        int signupResult = memberService.signup(memberDTO);

        if (signupResult > 0){
            return "signupSuccess";
        } else {
            return "signupForm";
        }
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session,
                        @RequestParam("memberEmail") String memberEmail,
                        @RequestParam("memberPassword") String memberPassword) {

        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("memberEmail", memberDTO.getMemberEmail());
            return "home";
        } else {
            return "redirect:/member/login";
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
    // 회원 목록 보기
    @GetMapping("memberList")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findMemberList(model);
        model.addAttribute("memberList", memberDTOList);
        return "memberList";
    }



}
