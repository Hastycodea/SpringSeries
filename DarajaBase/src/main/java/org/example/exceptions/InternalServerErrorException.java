package org.example.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InternalServerErrorException extends RuntimeException {

    private final String title, message;
    private final Exception e;

    @Override
    public String getMessage() {
        return message;
    }
}
