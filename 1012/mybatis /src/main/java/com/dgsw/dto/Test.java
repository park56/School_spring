package com.dgsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
@ToString   // toString 자동생성
@Getter // getter 자동생성
@Setter // setter 자동생성
@AllArgsConstructor //모든 파라미터가 있는 생성자 자동생성
public class Test {

    private Integer idx;    // int로 하면 null값을 허용하지 못하는데 Integer로 객체로 받아 null을 허용

    @NotEmpty   // 빈공간 허용 X
    @Length(min = 4)
    private String aa;

    @NotEmpty
    @Length(min = 4)
    private String bb;

}
