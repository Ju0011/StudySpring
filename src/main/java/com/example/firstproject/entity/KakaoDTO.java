package com.example.firstproject.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {
    private long id;
    private String email;
    private String nickname;
}