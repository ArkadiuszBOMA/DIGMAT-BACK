package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.AppUserLoginDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.*;
import com.codecool.dogmate.mapper.AppUserMapper;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.AppUserRepository;
import com.codecool.dogmate.repository.UserTypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
        AppUser entity = appUserMapper.mapNewAppUserDtoToEntity(appuser);
        UserType userType = userTypeRepository.findOneById(appuser.userType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setUserType(userType);
        userType.getAppUsers().add(entity);
        City city = cityRepository.findOneById(appuser.cityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setCity(city);
        city.getAppUsers().add(entity);
        entity.setPassword(passwordEncoder.encode(appuser.password()));
        AppUser savedEntity = appUserRepository.save(entity);
        appUserMapper.mapEntityToAppUserDto(savedEntity);
    }
}
