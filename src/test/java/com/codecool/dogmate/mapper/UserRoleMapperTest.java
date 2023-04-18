package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.userrole.NewUserRoleDto;
import com.codecool.dogmate.dto.userrole.UserRoleDto;
import com.codecool.dogmate.entity.UserRole;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserRoleMapperTest {

    private final UserRoleMapper userRoleMapper = new UserRoleMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapNewUserTypeDtoToEntity() {
        //given
        UserRole userRole = easyRandom.nextObject(UserRole.class);
        //when:
        UserRoleDto actual = userRoleMapper.mapEntityToUserTypeDto(userRole);
        //then:
        UserRoleDto expected = new UserRoleDto(
                userRole.getId(),
                userRole.getName(),
                userRole.getDate_create(),
                userRole.getDate_modify(),
                userRole.getDate_archive(),
                userRole.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToUserTypeDto() {
        //when:
        NewUserRoleDto userRoleDto = easyRandom.nextObject(NewUserRoleDto.class);
        //then:
        UserRole actual = userRoleMapper.mapNewUserTypeDtoToEntity(userRoleDto);

        ssertUserRoleEntityIsCorrect(userRoleDto,actual);

    }

    private void ssertUserRoleEntityIsCorrect(NewUserRoleDto userRoleDto, UserRole actual) {
        assertThat(userRoleDto.name()).isEqualTo(actual.getName());
    }
}