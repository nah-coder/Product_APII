package com.example.Product_APII.Service;//package com.example.demo.Service;

import com.example.Product_APII.DTO.Request.UserCreationRequest;
import com.example.Product_APII.DTO.Response.GetAllResponse;
import com.example.Product_APII.DTO.Response.UserCreatetionResponse;
import com.example.Product_APII.Entity.Role;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.UserMapper;
import com.example.Product_APII.Repository.RoleRepository;
import com.example.Product_APII.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<GetAllResponse> FindAll(){
        List<User> users = usersRepository.findAll();
        return userMapper.toGetAllResponseList(users);
    }

    public String createUser(UserCreationRequest request,String email) {
        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Mã hóa mật khẩu trước khi lưu

        // Lấy role USER từ database
        Role userRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        // Gán role mặc định
        user.setRoles(Set.of(userRole));

        usersRepository.save(user);
        return "Register completed";
    }
}
