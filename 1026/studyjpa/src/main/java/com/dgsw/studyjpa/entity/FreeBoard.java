package com.dgsw.studyjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor // 모든파라메타가 있는 생성자
@NoArgsConstructor // 기본생성자
@Builder    // builder 객체 생성
@ToString
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String writer;
    private String title;
    private String content;
    private LocalDateTime wdate;
}