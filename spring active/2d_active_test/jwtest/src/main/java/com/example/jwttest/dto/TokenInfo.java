package com.example.jwttest.dto;

import com.example.jwttest.response.CommonResponse;
import lombok.*;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
public class TokenInfo extends CommonResponse {

    private String accessToken;
}
