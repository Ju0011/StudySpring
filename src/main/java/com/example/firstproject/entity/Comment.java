package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        //예외발생
        if (dto.getId() != null) throw new IllegalArgumentException("댓글생성실패! 댓글의 id가 이미 있음");
        if (dto.getArticleId() != null) throw new IllegalArgumentException("댓글생성실패! 게시글의 id가 잘못됌");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),    //댓글 아이디
                article,        //댓글 단 부모 게시물
                dto.getNickname(),  //댓글 닉네임
                dto.getBody()       //댓글 본문
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생
        if (this.id != dto.getId()) throw new IllegalArgumentException("댓글수정실패! 댓글의 id가 없음");

        //객체 갱신
        if(dto.getNickname() != null) this.nickname = dto.getNickname();    //수정할 닉네임 데이터가 있다면 내용 반영
        if (dto.getBody() != null) this.body = dto.getBody();   // 수정할 댓글의 본문 데이터가 있다면 반영
    }
}
