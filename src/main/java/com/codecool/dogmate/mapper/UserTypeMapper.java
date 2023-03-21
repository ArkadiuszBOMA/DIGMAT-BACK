package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserTypeMapper {

//    private final UserMapper userMapper;
//
//    public UserTypesMapper(UserMapper userMapper) {this.userMapper = userMapper; }

    public UserType mapNewUserTypeDtoToEntity(NewUserTypeDto dto) {return new UserType(dto.name());}

    public UserTypeDto mapEntityToUserTypeDto(UserType entity) {
        return new UserTypeDto(
                entity.getId(),
                entity.getName()
        );
    }
}
