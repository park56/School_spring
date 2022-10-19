package com.dgsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Board {
        // 제목
        private String title;
        // 내용
        private String content;
        // 작성자
        private String username;
        // 글쓴 시간
        private Date createdAt;
        // 읽은 횟수
        private Integer watched;
}
