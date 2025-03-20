package com.example.Product_APII.Service;//package com.example.demo.Service;

import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> FindAll(){
        return usersRepository.findAll();
    }
}
