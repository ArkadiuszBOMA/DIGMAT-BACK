package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.entity.UserType;
import com.codecool.dogmate.mapper.UserTypeMapper;
import com.codecool.dogmate.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UserTypeDto createUserType(NewUserTypeDto usertype) {
        UserType entity = userTypeMapper.mapNewUserTypeDtoToEntity(usertype);
        UserType savedEntity = userTypeRepository.save(entity);
        return userTypeMapper.mapEntityToUserTypeDto(savedEntity);
    }
}
