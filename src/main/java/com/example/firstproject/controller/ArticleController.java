package com.example.firstproject.controller;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new") //자동으로 @GetMapping 패키지 임포트
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create") //자동으로 @GetMapping 패키지 임포트
    public String createArticleForm(ArticleForm form) { //폼 데이터를 DTO로 받기
        System.out.println(form.toString());    //DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO 를 엔티티로 변환
        Article article = form.toEntity();      //toEntity() 메소드는 DTO인 form 객체를 엔티티 객체로 변환하는 역할
        System.out.println(article.toString());

        // 2. 라파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);    //article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString());
        return "";
    }
}
