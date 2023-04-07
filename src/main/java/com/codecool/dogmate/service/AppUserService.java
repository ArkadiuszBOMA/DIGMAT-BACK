package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.*;
import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.dto.appuser.UpdateAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.UserType;
import com.codecool.dogmate.mapper.AppUserMapper;
import com.codecool.dogmate.repository.AppUserRepository;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final UserTypeRepository userTypeRepository;
    private final CityRepository cityRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, UserTypeRepository userTypeRepository, CityRepository cityRepository, AppUserMapper appUserMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userTypeRepository = userTypeRepository;
        this.cityRepository = cityRepository;
        this.appUserMapper = appUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUserDto> getAppUsers() {
        return appUserRepository.findAllBy().stream()
                .sorted(Comparator.comparing(AppUser::getDate_create))
                .map(appUserMapper::mapEntityToAppUserDto)
                .toList();
    }

    public List<AppUserDto> getAppUsers(Pageable pageable) {
        return appUserRepository.findAllBy(pageable).stream()
                .map(appUserMapper::mapEntityToAppUserDto)
                .toList();
    }
    public AppUserDto getAppUserById(Integer id) {
        return appUserRepository.findOneById(id)
                .map(appUserMapper::mapEntityToAppUserDto)
                .orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public AppUserDto login(String email, String password) {
        return appUserRepository.findOneByEmail(email)
                .map(appUserMapper::mapEntityToAppUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<AppUserDto> getAppUserByName(String name) {
        return appUserRepository.findAllByName(name).stream()
            .map(appUserMapper::mapEntityToAppUserDto)
            .toList();
    }

    public void createAppUser(NewAppUserDto appuser) {
//  metody walidacyjne "backlendu"
        AppUser entity = appUserMapper.mapNewAppUserDtoToEntity(appuser);
        UserType userType = userTypeRepository.findOneById(appuser.userType())
                .orElseThrow(() -> new UserTypeNotFoundException(appuser.userType()));
        entity.setUserType(userType);
        userType.getAppUsers().add(entity);
        City city = cityRepository.findOneById(appuser.cityId())
                .orElseThrow(() -> new CityNotFoundException(appuser.cityId()));
        entity.setCity(city);
        city.getAppUsers().add(entity);
        entity.setPassword(passwordEncoder.encode(appuser.password()));
        AppUser savedEntity = appUserRepository.save(entity);
        appUserMapper.mapEntityToAppUserDto(savedEntity);
    }

    public void updateAppUser(UpdateAppUserDto user) {
        UserType userType = userTypeRepository.findOneByName(user.userType())
                .orElseThrow(() -> new UserTypeNotFoundException(user.userType()));
        City city = cityRepository.findOneByName(user.city())
                .orElseThrow(() -> new CityNotFoundException(user.city()));
        log.info("Zaktualizowałem dane dla id {}", user.id());
        AppUser updatedAppUser = appUserRepository.findOneById(user.id())
                .orElseThrow(() -> new AppUserNotFoundException(user.id()));
        updatedAppUser.setFirst_name(user.firstName().trim().toUpperCase().replaceAll("( )+", " "));
        updatedAppUser.setLast_name(user.lastName().trim().toUpperCase().replaceAll("( )+", " "));
        updatedAppUser.setPassword(passwordEncoder.encode(user.password()));
        updatedAppUser.setProfilePictureLocation(user.profilePictureLocation());
        updatedAppUser.setAvatarSmallLocation(user.avatarSmallLocation());
        updatedAppUser.setUserType(userType);
        updatedAppUser.setCity(city);
        updatedAppUser.setIsLocked(user.isLocked());
        updatedAppUser.setIsBanned(user.isBanned());
        updatedAppUser.setBanExpiration(user.banExpiration());
        updatedAppUser.setIsActive(user.isActive());
        updatedAppUser.setDate_modify(LocalDateTime.now());
        appUserRepository.save(updatedAppUser);
    }

    public void archiveAppUser(Integer id) {
        AppUser archivedAppUser = appUserRepository.findOneById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));

        if(!archivedAppUser.getArchive()) {
            archivedAppUser.setDate_archive(LocalDateTime.now());
            archivedAppUser.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        appUserRepository.save(archivedAppUser);
    }
}
