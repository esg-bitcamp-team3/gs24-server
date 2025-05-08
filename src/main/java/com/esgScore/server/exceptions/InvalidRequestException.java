package com.esgScore.server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
public class InvalidRequestException extends RuntimeException {
    private List<String> invalidFields;

    public InvalidRequestException(String message, List<String> invalidFields) {
        super(message);
        this.invalidFields = invalidFields;
    }

}