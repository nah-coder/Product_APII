package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.UserCreationRequest;
import com.example.Product_APII.DTO.Response.GetAllResponse;
import com.example.Product_APII.DTO.Response.GetUserResponse;
import com.example.Product_APII.DTO.Response.UserCreatetionResponse;
import com.example.Product_APII.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserCreatetionResponse toUserResponse(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "avatar", target = "avatar"),
            @Mapping(source = "dateOfBirth", target = "dateOfBirth"),
            @Mapping(source = "deliveryAddress", target = "deliveryAddress"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "isActive", target = "isActive"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber", target = "phoneNumber"),
            @Mapping(source = "sex", target = "sex"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "roles", target = "roles")
    })
    GetUserResponse toGetUserResponse(User user);

    List<GetAllResponse> toGetAllResponseList(List<User> users);
}
