package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.userprivilege.NewUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UserPrivilegeDto;
import com.codecool.dogmate.entity.UserPrivilege;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserPrivilegeMapperTest {

    private final UserPrivilegeMapper userPrivilegeMapper = new UserPrivilegeMapper();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void mapNewUserRoleDtoToEntity() {
        //given:
        UserPrivilege userPrivilege = easyRandom.nextObject(UserPrivilege.class);
        //when:
        UserPrivilegeDto actual = userPrivilegeMapper.mapEntityToUserRoleDto(userPrivilege);
        //then:
        UserPrivilegeDto expected = new UserPrivilegeDto(
                userPrivilege.getId(),
                userPrivilege.getName(),
                userPrivilege.getDate_create(),
                userPrivilege.getDate_modify(),
                userPrivilege.getDate_archive(),
                userPrivilege.getArchive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToUserRoleDto() {
        //when:
        NewUserPrivilegeDto newUserPrivilegeDto = easyRandom.nextObject(NewUserPrivilegeDto.class);
        //then:
        UserPrivilege actual = userPrivilegeMapper.mapNewUserRoleDtoToEntity(newUserPrivilegeDto);
        assertUserPrivilegeEntityIsCorrect(newUserPrivilegeDto,actual);
    }

    private void assertUserPrivilegeEntityIsCorrect(NewUserPrivilegeDto newUserPrivilegeDto, UserPrivilege actual) {
        assertThat(newUserPrivilegeDto.name()).isEqualTo(actual.getName());
    }
}