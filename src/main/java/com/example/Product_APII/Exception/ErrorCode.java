package com.example.Product_APII.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed!",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1042,"Invalid message key!",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1005,"Unauthorized!",HttpStatus.UNAUTHORIZED),
    INVALID_OTP(1008,"Otp not exist!",HttpStatus.NOT_FOUND),
    OTP_EXCEEDED_ATTEMPTS(1009,"otp input exceeded!",HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(1010,"Expired otp!",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004,"user not existed!",HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1012,"token invalid!",HttpStatus.NOT_FOUND),
    PASSWORDS_DO_NOT_MATCH(1007,"Passwords are not duplicates!",HttpStatus.BAD_REQUEST),
    CANNOT_RESET_PASSWORD(1011,"Can not reset password!",HttpStatus.BAD_REQUEST),


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
