package org.flotares.tournaments.exception;

import lombok.Data;

@Data
public class ServiceExceptionMessage {
    private int code;
    private String message;
    private String description;

    public ServiceExceptionMessage(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
