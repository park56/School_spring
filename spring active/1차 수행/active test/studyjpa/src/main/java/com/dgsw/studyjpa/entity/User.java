package com.dgsw.studyjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(nullable = false)
    private String id;              // id

    @Column(nullable = false)
    private String pw;              // pw

    //@OneToMany
    //@JoinColumn(name = "username", referencedColumnName = "id")
    //private List<Board> boards;     // 내 게시물
}