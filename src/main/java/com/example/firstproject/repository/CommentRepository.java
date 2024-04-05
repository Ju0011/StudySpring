package com.example.firstproject.repository;
import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

// JpaRepository 상속받음
public interface CommentRepository extends JpaRepository<Comment, Long> {     //관리대상 엔티티 클래스타입(Comment), 관리대상 엔티티 대푯값 타입(Long)
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true) //value속성에 실행하려는 쿼리 작성

    // 특정 게시글의 모든 댓글 조회
    List<Comment> findByArticleId(Long articleId);
    //특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);    // Iterable -> ArrayList 수정
}
