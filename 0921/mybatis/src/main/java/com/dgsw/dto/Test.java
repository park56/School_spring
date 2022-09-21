package com.dgsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Test {

    @NotEmpty   // 빈공간 허용 X
    @Length(min = 4)
    private String aa;

    @NotEmpty
    @Length(min = 4)
    private String bb;
}
