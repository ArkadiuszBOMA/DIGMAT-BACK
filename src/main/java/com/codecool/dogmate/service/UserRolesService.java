package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.UserRoleNotFoundException;
import com.codecool.dogmate.advice.Exceptions.UserTypeNotFoundException;
import com.codecool.dogmate.dto.userrole.NewUserRoleDto;
import com.codecool.dogmate.dto.userrole.UpdateUserRoleDto;
import com.codecool.dogmate.dto.userrole.UserRoleDto;
import com.codecool.dogmate.entity.UserRole;
import com.codecool.dogmate.mapper.UserRoleMapper;
import com.codecool.dogmate.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserRolesService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRolesService(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    public List<UserRoleDto> getUserRole() {
        return userRoleRepository.findAllBy().stream()
                .map(userRoleMapper::mapEntityToUserRoleDto)
                .toList();
    }

    public List<UserRoleDto> getUserRole(Pageable pageable) {
        return userRoleRepository.findAllBy().stream()
                .map(userRoleMapper::mapEntityToUserRoleDto)
                .toList();
    }

    public UserRoleDto getUserRoleById(Integer id) {
        return userRoleRepository.findOneById(id)
                .map(userRoleMapper::mapEntityToUserRoleDto)
                .orElseThrow(() -> new UserTypeNotFoundException(id));
    }


    public UserRoleDto getUserRoleByName(String name) {
        return userRoleRepository.findOneByName(name)
                .map(userRoleMapper::mapEntityToUserRoleDto)
                .orElseThrow(() -> new UserTypeNotFoundException(name));
    }

    public UserRoleDto createUserRole(NewUserRoleDto userRole) {
        log.info("Rejestracja {}", userRole);
        UserRole entity = userRoleMapper.mapNewUserRoleDtoToEntity(userRole);
        UserRole savedEntity = userRoleRepository.save(entity);
        return userRoleMapper.mapEntityToUserRoleDto(savedEntity);
    }

    public void updateUserRole(UpdateUserRoleDto userRole) {
        log.info("Zaktualizowałem dane dla id {}", userRole.id());
        UserRole updateUserRole = userRoleRepository.findOneById(userRole.id())
                .orElseThrow(() -> new UserTypeNotFoundException(userRole.id()));
        updateUserRole.setName(userRole.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateUserRole.setDate_modify(LocalDateTime.now());
        userRoleRepository.save(updateUserRole);
    }

    public void archiveUserRole(Integer id) {
        UserRole archivedUserRole = userRoleRepository.findOneById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
        if(!archivedUserRole.getArchive()) {
            archivedUserRole.setDate_archive(LocalDateTime.now());
            archivedUserRole.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        userRoleRepository.save(archivedUserRole);
    }

    public void deleteUserRoleData(Integer id) {
        UserRole deletedUserRole = userRoleRepository.findOneById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
        log.info("Usunąłeś typ użytkownika o id {}", id);
        userRoleRepository.deleteById(deletedUserRole.getId());
    }
}
