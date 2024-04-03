package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString   // toString 함수 대신 사용 가능
public class ArticleForm {
    private Long id;
    private String title;   //제목 받을 필드
    private String content; //내용 받을 필드

//    //전송받은 제목과 내용을 필드에 저장하는 생성자 추가
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }


    //데이터 잘 받았는지 확인하기 위해 toString()
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
