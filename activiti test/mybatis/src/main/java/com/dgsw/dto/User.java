package com.dgsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    // id
    @NotEmpty
    private String id;
    // pwd
    @NotEmpty
    private String pw;
}