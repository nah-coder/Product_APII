package com.example.Product_APII.Service;

import com.example.Product_APII.Entity.Role;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Repository.IUserSecurityService;
import com.example.Product_APII.Repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSecurityService implements IUserSecurityService {
    UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

//        if(!user.isActive()){
//            throw new CustomException("Account has not been activated",HttpStatus.FORBIDDEN);
//        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),userAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> userAuthority(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
