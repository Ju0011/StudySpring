package com.example.firstproject.entity;

import com.example.firstproject.entity.KakaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MsgEntity {
    String message;
    KakaoDTO kakaoDTO;
}