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
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "mypage";
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
    // 회원 목록 보기 추후에 관리자 권한으로만 갈 수 있게하기
    @GetMapping("memberList")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findMemberList(model);
        model.addAttribute("memberList", memberDTOList);
        return "memberList";
    }

    @GetMapping("update")
    public String updateForm(Model model , HttpSession session){
        // 세션에 저장된 이메일 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);

        return "update";

    }

    @PostMapping("update")
    public String update (@ModelAttribute MemberDTO memberDTO){
        boolean result = memberService.update(memberDTO);
        if (result) {
            return "redirect:/member?memberIdx=" + memberDTO.getMemberIdx();
        } else {
            return "home";
        }
    }

    @GetMapping
    public String findByIdx(@RequestParam("memberIdx") int memberIdx , Model model){
        MemberDTO memberDTO = memberService.findByIdx(memberIdx);
        model.addAttribute("member",memberDTO);
        return "mypage";

    }

}
