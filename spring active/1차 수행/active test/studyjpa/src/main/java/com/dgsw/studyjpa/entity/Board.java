package com.dgsw.studyjpa.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;              // 임의의 식별자(기본키)

    @Column(nullable = false, length = 100)
    private String title;           // 제목

    @Column(length = 1000)
    private String content;         // 내용

    @Column(length = 50)
    private String username;        // 글쓴이

    private String imageName;       // 이미지 이름

    private Integer views;           // 조회수

    private Integer likes;           // 좋아요

    @CreationTimestamp
    private LocalDateTime cdate;    // 생성일
}


