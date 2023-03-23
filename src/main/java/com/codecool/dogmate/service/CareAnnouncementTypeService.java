package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.entity.CareAnnouncementType;
import com.codecool.dogmate.mapper.CareAnnouncementTypeMapper;
import com.codecool.dogmate.repository.CareAnnouncementTypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CareAnnouncementTypeService {

    private final CareAnnouncementTypeRepository careAnnouncementTypeRepository;
    private final CareAnnouncementTypeMapper careAnnouncementTypeMapper;

    public CareAnnouncementTypeService(CareAnnouncementTypeRepository careAnnouncementTypeRepository, CareAnnouncementTypeMapper careAnnouncementTypeMapper) {
        this.careAnnouncementTypeRepository = careAnnouncementTypeRepository;
        this.careAnnouncementTypeMapper = careAnnouncementTypeMapper;
    }


    public List<CareAnnouncementTypeDto> getCareAnnouncementTypes() {
        return careAnnouncementTypeRepository.findAllBy().stream()
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .toList();
    }

    public List<CareAnnouncementTypeDto> getCareAnnouncementTypes(Pageable pageable) {
        return careAnnouncementTypeRepository.findAllBy(pageable).stream()
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .toList();
    }

    public CareAnnouncementTypeDto getCareAnnouncementTypeById(Integer id) {
        return careAnnouncementTypeRepository.findOneById(id)
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CareAnnouncementTypeDto createCareAnnouncementType(NewCareAnnouncementTypeDto careannouncementtype) {
        CareAnnouncementType entity = careAnnouncementTypeMapper.mapCareAnnouncementTypeDtoToEntity(careannouncementtype);
        CareAnnouncementType savedEntity = careAnnouncementTypeRepository.save(entity);
        return careAnnouncementTypeMapper.mapEntityToCareAnnouncementTypeDto(savedEntity);
    }
}
