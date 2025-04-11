package com.example.Product_APII.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed!",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002,"Invalid message key!",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003,"Unauthorized!",HttpStatus.UNAUTHORIZED),
    INVALID_OTP(1004,"Otp not exist!",HttpStatus.NOT_FOUND),
    OTP_EXCEEDED_ATTEMPTS(1005,"otp input exceeded!",HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(1006,"Expired otp!",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1007,"user not existed!",HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1008,"token invalid!",HttpStatus.NOT_FOUND),
    PASSWORDS_DO_NOT_MATCH(1009,"Passwords are not duplicates!",HttpStatus.BAD_REQUEST),
    CANNOT_RESET_PASSWORD(1010,"Can not reset password!",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1011,"Can not found role",HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(1012,"Can not found category",HttpStatus.NOT_FOUND),
    MUSTNOTBELESSTHANZERO(1013, "Page and size must not be less than zero",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1014, "Can not found product",HttpStatus.NOT_FOUND),
    WISHLIST_NOT_FOUND(1015, "Can not found wishlist",HttpStatus.NOT_FOUND),
    PAYMENT_NOT_FOUND(1016, "Can not found payment",HttpStatus.NOT_FOUND),
    DELIVERY_NOT_FOUND(1017, "Can not found delivery",HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1018, "Can not found order",HttpStatus.NOT_FOUND),


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
