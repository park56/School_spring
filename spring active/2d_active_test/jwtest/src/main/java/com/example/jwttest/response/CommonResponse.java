package com.example.jwttest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponse {
    boolean success;    // 성공여부
    int code;   // 상태코드
    String message; // 메세지

    public void setAllCommonResponse(int code, boolean bool, String message ) {
        setCode(code);
        setSuccess(bool);
        setMessage(message);
    }
}
