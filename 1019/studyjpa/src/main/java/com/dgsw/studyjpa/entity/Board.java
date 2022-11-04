package com.dgsw.studyjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity             // 테이블 만들기
@Getter
@Setter
@NoArgsConstructor  // 모든 파라메터가 있는 생성자
@AllArgsConstructor // 기본 생성자
@Builder            // build 객체 생성
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long boardcol;          // 기본키를 위한 임의의 컬럶

    @Column(nullable = false)
    private String title;           // 제목

    private String content;         // 내용

    @Column(nullable = false)
    private String username;        // 글쓴이

    private String views;           // 조회수

    private LocalDateTime cdate;    // 생성일
}
