package com.example.Product_APII.controller;//package com.example.demo.Controller;

import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {
        return usersService.FindAll();
    }
}
