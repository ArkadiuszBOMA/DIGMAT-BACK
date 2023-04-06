package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.UserTypeNotFoundException;
import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.entity.UserType;
import com.codecool.dogmate.mapper.UserTypeMapper;
import com.codecool.dogmate.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserTypesService {

    private final UserTypeRepository userTypeRepository;
    private final UserTypeMapper userTypeMapper;

    public UserTypesService(UserTypeRepository userTypeRepository, UserTypeMapper userTypeMapper) {
        this.userTypeRepository = userTypeRepository;
        this.userTypeMapper = userTypeMapper;
    }

    public List<UserTypeDto> getUserTypes() {
        return userTypeRepository.findAllBy().stream()
                .map(userTypeMapper::mapEntityToUserTypeDto)
                .toList();
    }

    public List<UserTypeDto> getUserTypes(Pageable pageable) {
        return userTypeRepository.findAllBy().stream()
                .map(userTypeMapper::mapEntityToUserTypeDto)
                .toList();
    }

    public UserTypeDto getUserTypeById(Integer id) {
        return userTypeRepository.findOneById(id)
                .map(userTypeMapper::mapEntityToUserTypeDto)
                .orElseThrow(() -> new UserTypeNotFoundException(id));
    }


    public UserTypeDto getUserTypeByName(String name) {
        return userTypeRepository.findOneByName(name)
                .map(userTypeMapper::mapEntityToUserTypeDto)
                .orElseThrow(() -> new UserTypeNotFoundException(name));
    }

    public UserTypeDto createUserType(NewUserTypeDto usertype) {
        log.info("Rejestracja {}", usertype);
        UserType entity = userTypeMapper.mapNewUserTypeDtoToEntity(usertype);
        UserType savedEntity = userTypeRepository.save(entity);
        return userTypeMapper.mapEntityToUserTypeDto(savedEntity);
    }

    public void updateUserType(UserTypeDto userType) {
        log.info("Zaktualizowałem dane dla id {}", userType.id());
        UserType updateUserType = userTypeRepository.findById(userType.id())
                .orElseThrow(() -> new UserTypeNotFoundException(userType.id()));
        updateUserType.setName(userType.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateUserType.setDate_modify(LocalDateTime.now());
        userTypeRepository.save(updateUserType);
    }

    public void archiveUserType(Integer id) {
        UserType archivedUserType = userTypeRepository.findById(id)
                .orElseThrow(() -> new UserTypeNotFoundException(id));
        if(!archivedUserType.getArchive()) {
            archivedUserType.setDate_archive(LocalDateTime.now());
            archivedUserType.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        userTypeRepository.save(archivedUserType);
    }
}
