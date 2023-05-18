package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.UserPrivilegesNotFoundException;
import com.codecool.dogmate.advice.Exceptions.UserRoleNotFoundException;
import com.codecool.dogmate.dto.userprivilege.NewUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UpdateUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UserPrivilegeDto;
import com.codecool.dogmate.entity.UserPrivilege;
import com.codecool.dogmate.mapper.UserPrivilegeMapper;
import com.codecool.dogmate.repository.UserPrivilegeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class UserPrivilegesService {

    private final UserPrivilegeRepository userPrivilegeRepository;
    private final UserPrivilegeMapper userPrivilegeMapper;

    public UserPrivilegesService(UserPrivilegeRepository userPrivilegeRepository, UserPrivilegeMapper userPrivilegeMapper) {
        this.userPrivilegeRepository = userPrivilegeRepository;
        this.userPrivilegeMapper = userPrivilegeMapper;
    }

    public List<UserPrivilegeDto> getUserPrivilege() {
        return userPrivilegeRepository.findAllBy().stream()
                .sorted(Comparator.comparing(UserPrivilege::getName))
                .map(userPrivilegeMapper::mapEntityToUserRoleDto)
                .toList();
    }

    public List<UserPrivilegeDto> getUserPrivilege(Pageable pageable) {
        return userPrivilegeRepository.findAllBy().stream()
                .sorted(Comparator.comparing(UserPrivilege::getName))
                .map(userPrivilegeMapper::mapEntityToUserRoleDto)
                .toList();
    }

    public UserPrivilegeDto getUserPrivilegeById(Integer id) {
        return userPrivilegeRepository.findOneById(id)
                .map(userPrivilegeMapper::mapEntityToUserRoleDto)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
    }


    public UserPrivilegeDto getUserPrivilegeByName(String name) {
        return userPrivilegeRepository.findOneByName(name)
                .map(userPrivilegeMapper::mapEntityToUserRoleDto)
                .orElseThrow(() -> new UserRoleNotFoundException(name));
    }

    public UserPrivilegeDto createUserPrivilege(NewUserPrivilegeDto userPrivilege) {
        log.info("Rejestracja {}", userPrivilege);
        UserPrivilege entity = userPrivilegeMapper.mapNewUserRoleDtoToEntity(userPrivilege);
        entity.setName(userPrivilege.name().trim().toUpperCase().replaceAll("( )+", " "));
        UserPrivilege savedEntity = userPrivilegeRepository.save(entity);
        return userPrivilegeMapper.mapEntityToUserRoleDto(savedEntity);
    }

    public UserPrivilegeDto updateUserPrivilege(UpdateUserPrivilegeDto userPrivilege) {
        log.info("Zaktualizowałem dane dla id {}", userPrivilege.id());
        UserPrivilege updateUserPrivilege = userPrivilegeRepository.findOneById(userPrivilege.id())
                .orElseThrow(() -> new UserPrivilegesNotFoundException(userPrivilege.id()));
        updateUserPrivilege.setName(userPrivilege.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateUserPrivilege.setDate_modify(LocalDateTime.now());
        userPrivilegeRepository.save(updateUserPrivilege);
        return userPrivilegeMapper.mapEntityToUserRoleDto(updateUserPrivilege);
    }

    public void archiveUserPrivilege(Integer id) {
        UserPrivilege archivedUserPrivilege = userPrivilegeRepository.findOneById(id)
                .orElseThrow(() -> new UserPrivilegesNotFoundException(id));
        if(!archivedUserPrivilege.getArchive()) {
            archivedUserPrivilege.setDate_archive(LocalDateTime.now());
            archivedUserPrivilege.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        userPrivilegeRepository.save(archivedUserPrivilege);
    }

    public void deleteUserPrivilegeData(Integer id) {
        userPrivilegeRepository.findOneById(id)
                .orElseThrow(() -> new UserPrivilegesNotFoundException(id));
        log.info("Usunąłeś typ użytkownika o id {}", id);
        userPrivilegeRepository.deleteById(id);
    }
}
