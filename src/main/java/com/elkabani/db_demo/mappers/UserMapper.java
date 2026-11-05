package com.elkabani.db_demo.mappers;

import com.elkabani.db_demo.dtos.RegisterUserRequest;
import com.elkabani.db_demo.dtos.UpdateUserRequest;
import com.elkabani.db_demo.dtos.UserDto;
import com.elkabani.db_demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
     void update(UpdateUserRequest request, @MappingTarget User user);
}


