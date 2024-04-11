package com.e2i1.linkeepserver.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponse {
    private String errorMessage;
}
