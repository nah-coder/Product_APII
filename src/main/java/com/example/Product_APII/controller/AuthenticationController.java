package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.AuthenticationRequest;
import com.example.Product_APII.DTO.Request.OtpRequest;
import com.example.Product_APII.DTO.Response.AuthenticationResponse;
import com.example.Product_APII.DTO.Response.OtpResponse;
import com.example.Product_APII.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
//    @Autowired
//    private RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<OtpResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        OtpResponse result = authenticationService.login(request);
        return ResponseEntity.ok(ApiResponse.<OtpResponse>builder()
                        .code(1000)
                .data(result).build());
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<ApiResponse<AuthenticationResponse>> RefreshToken(@RequestBody @Valid RefreshRequest  request) throws ParseException, JOSEException {
//        AuthenticationResponse result = authenticationService.refreshToken(request);
//        return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
//                .code(1000)
//                .data(result).build());
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse<String>> register(@RequestBody @Valid UserCreationRequest request) {
//        ApiResponse<String> response = new ApiResponse<>();
//        String register = registerService.Register(request);
//        response.setMessage(register);
//        return ResponseEntity.ok(response);
//    }

//    @PutMapping("/reset-password")
//    public ResponseEntity<ApiResponse<String>> ResetPassword(@RequestBody @Valid ForgetPasswordRequest forgetPasswordRequest) {
//        String forget = authenticationService.ResetPassword(forgetPasswordRequest);
//        return ResponseEntity.ok(ApiResponse.<String>builder()
//                .code(1000)
//                .data(forget).build());
//    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> verifyOTP(@RequestBody @Valid OtpRequest request) {
        AuthenticationResponse result = authenticationService.verifyOTP(request);
        return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .data(result).build());
    }

//    @PostMapping("/link-reset")
//    public ResponseEntity<ApiResponse<LinkResetResponse>> LinkResetResponse(@RequestBody @Valid EmailRequest emailRequest) {
//        LinkResetResponse result = authenticationService.LinkForgetPassword(emailRequest);
//        return ResponseEntity.ok(ApiResponse.<LinkResetResponse>builder()
//                .code(1000)
//                .data(result).build());
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<ApiResponse<Void>> Logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
//        authenticationService.logout(logoutRequest);
//        return ResponseEntity.ok(ApiResponse.<Void>builder()
//                .build());
//    }
}
