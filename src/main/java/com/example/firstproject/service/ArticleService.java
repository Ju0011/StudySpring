package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j  //로깅 어노테이션
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;    //게시글 레포지토리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();       //dto -> 엔티티로 변환 후 article에 저장
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article); // article을 DB에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);   //수정 없음
        // 3. 잘못된 요청 처리
        if(target == null || id != article.getId()){
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }
        // 4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated; // 응답은 컨트롤러가 하므로 여기서는 수정 데이터 반환
    }

    public Article delete(Long id) {
        // 1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);   // 수정없음

        // 2. 잘못된 요청 처리하기
        if(target == null){         // 응답은 컨트롤러가 하므로 여기서는 null 반환
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;          // DB에서 삭제한 대상을 컨트롤러에 반환

    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. DTO 묶음을 엔티티 묶음으로 변환
        List<Article> articleList = dtos.stream()   // dtos 스트림화, 밑 줄 모두 수행 후 최종결과를 articleList에 저장
                .map(dto -> dto.toEntity())         // map() 으로 dto 가 올때마다 dto.toEntity를 수행해 매핑
                .collect(Collectors.toList());      // 매핑한 것을 리스트로 묶기
        // 2. 엔티티 묶음을 DB에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)     // id가 -1인 데이터 찾기
                .orElseThrow(()-> new IllegalArgumentException("실패"));  // 찾는 데이터 없으면 예외 발생
        // 4. 결과 값 반환하기
        return articleList;
    }
}
