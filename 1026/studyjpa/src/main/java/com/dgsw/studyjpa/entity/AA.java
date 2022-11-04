package com.dgsw.studyjpa.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 테이블만들기
@Getter // Setter함수
@Setter // getter함수
@AllArgsConstructor // 모든파라메타가 있는 생성자
@NoArgsConstructor // 기본생성자
@Builder    // builder 객체 생성
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
