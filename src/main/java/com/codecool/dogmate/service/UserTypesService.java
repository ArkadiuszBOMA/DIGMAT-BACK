package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.UserTypeNotFoundException;
import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.dto.voivodeship.UpdateVoivodeshipDto;
import com.codecool.dogmate.entity.UserType;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.UserTypeMapper;
import com.codecool.dogmate.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

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
}
