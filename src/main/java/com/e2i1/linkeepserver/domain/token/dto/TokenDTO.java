package com.e2i1.linkeepserver.domain.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {
    private String token;

    private LocalDateTime expiredAt;
}
