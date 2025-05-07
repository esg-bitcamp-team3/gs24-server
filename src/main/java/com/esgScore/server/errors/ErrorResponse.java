package com.esgScore.server.errors;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
    private List<String> invalidFields;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
