package com.example.firstproject.controller;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

import java.util.List;

@Slf4j  //로깅을 위한 어노테이션 (println)
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
        log.info(form.toString());  //로깅코드
        //System.out.println(form.toString());    //DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO 를 엔티티로 변환
        Article article = form.toEntity();      //toEntity() 메소드는 DTO인 form 객체를 엔티티 객체로 변환하는 역할
        log.info(article.toString());  //로깅코드
        //System.out.println(article.toString());

        // 2. 라파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);    //article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());  //로깅코드
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping ("/articles/{id}")  // 데이터 조회 요청 접수 (URL값 확인)
    public String show(@PathVariable Long id, Model model){      // 매개변수로 id 받아오기
        log.info("id = " + id);     //id 잘 받았는지 확인하는 로그 찍기

        // 1. id 를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //id 값을 찾을 때 해당 값이 없으면 null값 반환

        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //findAll( ) 메서드가 반환하는 데이터 타입은 Iterable 인데 작성 타입은 List 라서 오류뜸
        //캐스팅을 사용한 형변환 -> ArticleRepository 에서 Override 해줌


        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        //뷰 페이지 설정하기
        return "articles/edit";
    }


    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        
        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        
        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        
        // 2-2. 기존 데이터 값 갱신하기
        if (target != null){
            articleRepository.save(articleEntity);  //엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }
}
