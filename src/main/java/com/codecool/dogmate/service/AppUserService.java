package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.AnimalTypeNotFoundException;
import com.codecool.dogmate.advice.Exceptions.AppUserNotFoundException;
import com.codecool.dogmate.advice.Exceptions.UserRoleNotFoundException;
import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.entity.UserRole;
import com.codecool.dogmate.mapper.AppUserMapper;
import com.codecool.dogmate.repository.AppUserRepository;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.UserRoleRepository;
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
    private final UserRoleRepository userRoleRepository;
    private final CityRepository cityRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, UserRoleRepository userRoleRepository, CityRepository cityRepository, AppUserMapper appUserMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userRoleRepository = userRoleRepository;
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
//  metody walidacyjne "backendu"
        AppUser newAppUser = appUserMapper.mapNewAppUserDtoToEntity(appuser, passwordEncoder.encode(appuser.password()));
        UserRole userRole = userRoleRepository.findOneByName("USER")
                .orElseThrow(() -> new UserRoleNotFoundException("USER"));
        newAppUser.addUserRole(userRole);
        userRole.addAppUser(newAppUser);
        AppUser savedEntity = appUserRepository.save(newAppUser);
        appUserMapper.mapEntityToAppUserDto(savedEntity);
    }

//    public void updateAppUser(UpdateAppUserDto user) {
//        UserRole userRole = userRoleRepository.findOneByName(user.userType())
//                .orElseThrow(() -> new UserRoleNotFoundException(user.userType()));
//        City city = cityRepository.findOneByName(user.city())
//                .orElseThrow(() -> new CityNotFoundException(user.city()));
//        log.info("Zaktualizowałem dane dla id {}", user.id());
//        AppUser updatedAppUser = appUserRepository.findOneById(user.id())
//                .orElseThrow(() -> new AppUserNotFoundException(user.id()));
//        updatedAppUser.setFirst_name(user.firstName().trim().toUpperCase().replaceAll("( )+", " "));
//        updatedAppUser.setLast_name(user.lastName().trim().toUpperCase().replaceAll("( )+", " "));
//        updatedAppUser.setPassword(passwordEncoder.encode(user.password()));
//        updatedAppUser.setProfilePictureLocation(user.profilePictureLocation());
//        updatedAppUser.setAvatarSmallLocation(user.avatarSmallLocation());
//        updatedAppUser.setUserType(userRole);
//        updatedAppUser.setCity(city);
//        updatedAppUser.setIsLocked(user.isLocked());
//        updatedAppUser.setIsBanned(user.isBanned());
//        updatedAppUser.setBanExpiration(user.banExpiration());
//        updatedAppUser.setIsActive(user.isActive());
//        updatedAppUser.setDate_modify(LocalDateTime.now());
//        appUserRepository.save(updatedAppUser);
//    }

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

    public void deleteAppUserData(Integer id) {
        AppUser deletedAppUser = appUserRepository.findOneById(id)
                .orElseThrow(() -> new AnimalTypeNotFoundException(id));
        log.info("Usunąłeś użytkownika o id {}", id);
        appUserRepository.deleteById(deletedAppUser.getId());
    }

}
