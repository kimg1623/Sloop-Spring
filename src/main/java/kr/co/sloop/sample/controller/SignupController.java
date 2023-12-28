package kr.co.sloop.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class SignupController {

    @GetMapping("/signup")
    public String signupForm(){
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(){
        return "home";
    }
}
