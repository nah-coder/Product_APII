package com.example.Product_APII.controller;//package com.example.demo.Controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.UserCreationRequest;
import com.example.Product_APII.DTO.Response.GetAllResponse;
import com.example.Product_APII.DTO.Response.UserCreatetionResponse;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<GetAllResponse>>> getAllUsers() {
        ApiResponse<List<GetAllResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(usersService.FindAll());
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody @Valid UserCreationRequest request , Authentication authentication) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(usersService.createUser(request, authentication.getName()));
        return ResponseEntity.ok(apiResponse);
    }
}
