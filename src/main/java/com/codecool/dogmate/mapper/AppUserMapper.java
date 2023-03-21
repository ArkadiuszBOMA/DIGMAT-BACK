package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.UserType;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.UserTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    private final AnimalMapper animalMapper;
    private final UserTypeRepository userTypeRepository;
    private final CityRepository cityRepository;

    public AppUserMapper(AnimalMapper animalMapper, UserTypeRepository userTypeRepository, CityRepository cityRepository) {
        this.animalMapper = animalMapper;
        this.userTypeRepository = userTypeRepository;
        this.cityRepository = cityRepository;
    }

    public AppUser mapNewAppUserDtoToEntity(NewAppUserDto dto) {return new AppUser(
            dto.firstName(),
            dto.lastName(),
            dto.email(),
            dto.password()
    );}

    public AppUserDto mapEntityToAppUserDto(AppUser entity) {
        return new AppUserDto(
                entity.getId(),
                entity.getFirst_name(),
                entity.getLast_name(),
                entity.getEmail(),
                entity.getProfilePictureLocation(),
                entity.getAvatarSmallLocation(),
                entity.getUserType().getId(),
                entity.getCity().getId(),
                entity.getDescription(),
                entity.getIsLocked(),
                entity.getIsBanned(),
                entity.getBanExpiration(),
                entity.getIsActive(),
                entity.getAnimals().stream()
                        .map(animalMapper::mapEntityToAnimalDto)
                        .toList()

        );
    }
}
