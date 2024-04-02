package com.example.firstproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/articles/new") //자동으로 @GetMapping 패키지 임포트
    public String newArticleForm() {
        return "articles/new";
    }
}
