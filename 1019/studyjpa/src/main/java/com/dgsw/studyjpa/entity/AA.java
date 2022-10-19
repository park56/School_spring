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
public class AA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String aa;
    private String bb;

    private LocalDateTime cdate;
    private LocalDateTime mdate;

    private int count;
}
