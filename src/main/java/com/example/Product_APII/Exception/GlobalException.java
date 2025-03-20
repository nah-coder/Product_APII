package com.example.Product_APII.Exception;


import com.example.Product_APII.DTO.Request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ApiResponse> handling(Exception exception){
//        ApiResponse apiReponse = new ApiResponse();
//        apiReponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiReponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage() + exception.getClass().getName());
//        return ResponseEntity.badRequest().body(apiReponse);
//    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handlingException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiReponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        ErrorCode mainErrorCode = ErrorCode.INVALID_KEY;

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String enumKey = fieldError.getDefaultMessage();
            ErrorCode errorCode;
            try {
                errorCode = ErrorCode.valueOf(enumKey);
                if (mainErrorCode == ErrorCode.INVALID_KEY) {
                    mainErrorCode = errorCode;
                }
            } catch (IllegalArgumentException ex) {
                errorCode = ErrorCode.INVALID_KEY;
            }
            errors.put(fieldError.getField(), errorCode.getMessage());
        }

        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(mainErrorCode.getCode());
        apiResponse.setMessage(errors);

        return ResponseEntity.badRequest().body(apiResponse);
    }



//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        String enumKey = e.getFieldError().getDefaultMessage();
//        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
//        ApiResponse apiReponse = new ApiResponse();
//        apiReponse.setCode(errorCode.getCode());
//        apiReponse.setMessage(errorCode.getMessage());
//        return ResponseEntity.badRequest().body(apiReponse);
//    }

}

