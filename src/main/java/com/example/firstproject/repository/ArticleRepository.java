package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {     //관리대상 엔티티 클래스타입(Article), 관리대상 엔티티 대푯값 타입(Long)
    @Override
    ArrayList<Article> findAll();    // Iterable -> ArrayList 수정
}
