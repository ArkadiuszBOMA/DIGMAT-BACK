package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    private final AnimalMapper animalMapper;
    private final UserRoleRepository userRoleRepository;
    private final CityRepository cityRepository;

    public AppUserMapper(AnimalMapper animalMapper, UserRoleRepository userRoleRepository, CityRepository cityRepository) {
        this.animalMapper = animalMapper;
        this.userRoleRepository = userRoleRepository;
        this.cityRepository = cityRepository;
    }

    public AppUser mapNewAppUserDtoToEntity(NewAppUserDto dto, String encodedPassword) {
        return new AppUser(
                dto.email(),
                encodedPassword,
                dto.firstName(),
                dto.lastName()
        );
    }

    public AppUserDto mapEntityToAppUserDto(AppUser entity) {
        return new AppUserDto(
                entity.getId(),
                entity.getFirst_name(),
                entity.getLast_name(),
                entity.getEmail(),
                entity.getProfilePictureLocation(),
                entity.getAvatarSmallLocation(),
                entity.getCity().getName(),
                entity.getDescription(),
                entity.getIsLocked(),
                entity.getIsBanned(),
                entity.getBanExpiration(),
                entity.getIsActive()

        );
    }
}
