package com.dgsw.studyjpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter // Setter함수
@Setter // getter함수
@AllArgsConstructor // 모든파라메타가 있는 생성자
@NoArgsConstructor // 기본생성자
@Builder    // builder 객체 생성
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

}