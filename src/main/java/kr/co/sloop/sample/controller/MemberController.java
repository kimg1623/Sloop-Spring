package kr.co.sloop.sample.controller;


import kr.co.sloop.sample.domain.MemberDTO;
import kr.co.sloop.sample.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
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
            return "loginForm";
        } else {
            return "signupForm";
        }
    }
    // 이메일 중복확인 AJAX
    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO , HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);

        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "home";
        } else {
            return "login";
        }
    }

}
