package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Entity                 //해당 클래스가 엔티티임을 선언
@Getter                 //각 필드 값 조회 getter
@ToString               //모든 필드 출력하는 toString
@AllArgsConstructor     //모든 필드를 매개변수고 갖는 생성자 자동 생성
@NoArgsConstructor      //매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //DB가 자동으로 1씩 증가
    private Long id;        //대표키

    @ManyToOne  ///댓글과 본문을 다대일 관계로 설정
    @JoinColumn(name="article_id") //외래키 생성, Article 엔티티의 기본키와 매핑 - 댓글이 달린 본문의 id값 : article_id
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;
}
