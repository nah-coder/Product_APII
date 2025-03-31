package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Request.RoleRequest;
import com.example.Product_APII.DTO.Response.RoleResponse;
import com.example.Product_APII.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
