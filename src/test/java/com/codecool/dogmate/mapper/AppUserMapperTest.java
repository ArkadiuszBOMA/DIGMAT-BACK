package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.entity.AppUser;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppUserMapperTest {


    private final EasyRandom easyRandom = new EasyRandom();

    private final AnimalMapper animal = easyRandom.nextObject(AnimalMapper.class);

    private final AppUserMapper appUserMapper = new AppUserMapper(animal);


    @Test
    void mapNewAppUserDtoToEntity() {
        //given:
        AppUser appUser = easyRandom.nextObject(AppUser.class);
        //when:
        AppUserDto actual = appUserMapper.mapEntityToAppUserDto(appUser);
        //then:
        AppUserDto expected = new AppUserDto(
                appUser.getId(),
                appUser.getFirst_name(),
                appUser.getLast_name(),
                appUser.getEmail(),
                appUser.getProfilePictureLocation(),
                appUser.getAvatarSmallLocation(),
                appUser.getCity().getName(),
                appUser.getDescription(),
                appUser.getIsLocked(),
                appUser.getIsBanned(),
                appUser.getBanExpiration(),
                appUser.getIsActive()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mapEntityToAppUserDto() {
    }
}