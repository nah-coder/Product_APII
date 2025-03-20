package com.example.Product_APII.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed!",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1042,"Invalid message key!",HttpStatus.NOT_FOUND),
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
