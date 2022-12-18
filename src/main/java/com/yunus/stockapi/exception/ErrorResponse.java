package com.yunus.stockapi.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String code;

    private String message;
}
