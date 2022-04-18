package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfoDto {
    private Long id;  //final도 가능
    private String nickname;
    private String email;

    /* @AllArgsConstructor -> 얘 롬복이 해줌
    public KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
    */
}
