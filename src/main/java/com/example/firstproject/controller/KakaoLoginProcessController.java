package com.example.firstproject.controller;

import com.example.firstproject.entity.MsgEntity;
import com.example.firstproject.entity.KakaoDTO;
import com.example.firstproject.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoLoginProcessController {
    private final KakaoLoginService kakaoLoginService;
    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request )throws Exception {
        KakaoDTO kakaoInfo = kakaoLoginService.getKakaoInfo(
                request.getParameter("code"));
        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
    }
}
