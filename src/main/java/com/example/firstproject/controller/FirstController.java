package com.example.firstproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;    //Model 클래스 패키지 자동 임포트

@Controller
public class FirstController {
    @GetMapping("/hi") //자동으로 @GetMapping 패키지 임포트
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "주영");
        return "greetings";
        //서버가 알아서 templates 디렉터리에서
        //greetings.mustache 파일을 찾아 웹 브라우저로 전송
    }

    @GetMapping("/bye") //자동으로 @GetMapping 패키지 임포트
    public String seeYouNext(Model model){
        model.addAttribute("username", "주영");
        return "goodbye";
        //서버가 알아서 templates 디렉터리에서
        //greetings.mustache 파일을 찾아 웹 브라우저로 전송
    }
}
