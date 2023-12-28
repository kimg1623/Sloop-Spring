package kr.co.sloop.sample.controller;


import kr.co.sloop.sample.domain.MemberDTO;
import kr.co.sloop.sample.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            return "login";
        } else {
            return "signupForm";
        }
    }
}
