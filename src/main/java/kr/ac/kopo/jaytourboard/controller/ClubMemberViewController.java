package kr.ac.kopo.jaytourboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubMemberViewController {
    @GetMapping("/Login")
    public String login(){
        return "Login";
    }

    @GetMapping("/SignUp")
    public String signup(){
        return "SignUp";
    }
}
