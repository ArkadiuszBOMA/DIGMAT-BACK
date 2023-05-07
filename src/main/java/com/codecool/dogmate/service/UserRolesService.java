package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.UserRoleNotFoundException;
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
                .map(userRoleMapper::mapEntityToUserTypeDto)
                .toList();
    }

    public List<UserRoleDto> getUserRoles(Pageable pageable) {
        return userRoleRepository.findAllBy().stream()
                .map(userRoleMapper::mapEntityToUserTypeDto)
                .toList();
    }

    public UserRoleDto getUserRoleById(Integer id) {
        return userRoleRepository.findOneById(id)
                .map(userRoleMapper::mapEntityToUserTypeDto)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
    }


    public UserRoleDto getUserRoleByName(String name) {
        return userRoleRepository.findOneByName(name)
                .map(userRoleMapper::mapEntityToUserTypeDto)
                .orElseThrow(() -> new UserRoleNotFoundException(name));
    }

    public UserRoleDto createUserRole(NewUserRoleDto userrole) {
        log.info("Rejestracja {}", userrole);
        UserRole entity = userRoleMapper.mapNewUserTypeDtoToEntity(userrole);
        entity.setName(userrole.name().trim().toUpperCase().replaceAll("( )+", " "));
        UserRole savedEntity = userRoleRepository.save(entity);
        return userRoleMapper.mapEntityToUserTypeDto(savedEntity);
    }

    public UserRoleDto updateUserRole(UpdateUserRoleDto userrole) {
        log.info("Zaktualizowałem dane dla id {}", userrole.id());
        UserRole updateUserRole = userRoleRepository.findOneById(userrole.id())
                .orElseThrow(() -> new UserRoleNotFoundException(userrole.id()));
        updateUserRole.setName(userrole.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateUserRole.setDate_modify(LocalDateTime.now());
        userRoleRepository.save(updateUserRole);
        return userRoleMapper.mapEntityToUserTypeDto(updateUserRole);
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
        userRoleRepository.findOneById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
        log.info("Usunąłeś typ użytkownika o id {}", id);
        userRoleRepository.deleteById(id);
    }
}
