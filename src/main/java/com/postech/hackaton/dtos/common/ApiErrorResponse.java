package com.postech.hackaton.dtos.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
    private Integer statusCode;
    private String message;
    private String details;
    private Long timestamp;
}