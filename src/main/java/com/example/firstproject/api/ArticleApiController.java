package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Slf4j // 로그 어노테이션
//@RestController
//public class ArticleApiController {
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // GET
//    @GetMapping("/api/articles")
//    public List<Article> index(){
//        return articleRepository.findAll();
//    }
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//    @PostMapping("/api/articles")
//    public Article crate(@RequestBody ArticleForm dto){ //@RequestBody : 요청 시 body 데이터를 create메서드의 매개변수로 받아옴
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // PATCH - 수정
//    @PostMapping("/api/articles/{id}")
//    public ResponseEntity<Object> update(@RequestBody Long id, @RequestBody ArticleForm dto){ //@RequestBody : 요청 시 body 데이터를 create메서드의 매개변수로 받아옴
//        // 1. DTO -> 엔티티 전환하기
//        Article article = dto.toEntity();
//
//        // 2. 타깃 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리
//        if (target == null || id != article.getId()){
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id :{}, article :{}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        // 4. 업데이트 및 정상 응답하기
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 2. 잘못된 요청 처리
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//}

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?      //생성하면 OK, 실패하면 오류 응답 : BAD_REQUEST
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);   // 서비스 통해 게시글 수정
        return (updated != null) ?  // 수정되면 OK, 실패하면 오류 응답 : BAD_REQUEST
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 코드
   @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
       List<Article> createdList = articleService.createArticles(dtos);     // 서비스 호출
       return (createdList != null) ?       // 생성 결과에 따라 응답 처리
               ResponseEntity.status(HttpStatus.OK).body(createdList) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }
}