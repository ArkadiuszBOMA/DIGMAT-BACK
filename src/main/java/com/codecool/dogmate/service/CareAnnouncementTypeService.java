package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.CareTypeAnnouncementNotFoundException;
import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.entity.CareAnnouncementType;
import com.codecool.dogmate.mapper.CareAnnouncementTypeMapper;
import com.codecool.dogmate.repository.CareAnnouncementTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
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
                .sorted(Comparator.comparing(CareAnnouncementType::getName))
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .toList();
    }

    public List<CareAnnouncementTypeDto> getCareAnnouncementTypes(Pageable pageable) {
        return careAnnouncementTypeRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(CareAnnouncementType::getName))
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .toList();
    }

    public CareAnnouncementTypeDto getCareAnnouncementTypeById(Integer id) {
        return careAnnouncementTypeRepository.findOneById(id)
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .orElseThrow(() -> new CareTypeAnnouncementNotFoundException(id));
    }

    public CareAnnouncementTypeDto getCareAnnouncementTypeByName(String name) {
        return careAnnouncementTypeRepository.findOneByName(name)
                .map(careAnnouncementTypeMapper::mapEntityToCareAnnouncementTypeDto)
                .orElseThrow(() -> new CareTypeAnnouncementNotFoundException(name));
    }

    public CareAnnouncementTypeDto createCareAnnouncementType(NewCareAnnouncementTypeDto careannouncementtype) {
        CareAnnouncementType entity = careAnnouncementTypeMapper.mapCareAnnouncementTypeDtoToEntity(careannouncementtype);
        CareAnnouncementType savedEntity = careAnnouncementTypeRepository.save(entity);
        return careAnnouncementTypeMapper.mapEntityToCareAnnouncementTypeDto(savedEntity);
    }

    public void updateCareAnnouncementType(CareAnnouncementTypeDto careAnnouncementType) {
        log.info("Zaktualizowałem dane dla id {}", careAnnouncementType.id());
        CareAnnouncementType updateCareAnnouncementTypeData = careAnnouncementTypeRepository.findOneById(careAnnouncementType.id())
                .orElseThrow(() -> new CareTypeAnnouncementNotFoundException(careAnnouncementType.id()));
        updateCareAnnouncementTypeData.setName(careAnnouncementType.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateCareAnnouncementTypeData.setDate_modify(LocalDateTime.now());
        careAnnouncementTypeRepository.save(updateCareAnnouncementTypeData);
    }

    public void archiveCareAnnouncementType(Integer id) {
        CareAnnouncementType archivedCareAnnouncementTypeData = careAnnouncementTypeRepository.findOneById(id)
                .orElseThrow(() -> new CareTypeAnnouncementNotFoundException(id));
        if(!archivedCareAnnouncementTypeData.getArchive()) {
            archivedCareAnnouncementTypeData.setDate_archive(LocalDateTime.now());
            archivedCareAnnouncementTypeData.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        careAnnouncementTypeRepository.save(archivedCareAnnouncementTypeData);
    }
    public void deleteCareAnnouncementTypeData(Integer id) {
        CareAnnouncementType deletedCareAnnouncementType = careAnnouncementTypeRepository.findOneById(id)
                .orElseThrow(() -> new CareTypeAnnouncementNotFoundException(id));
        log.info("Usunąłeś rodzaj opieki o id {}", id);
        careAnnouncementTypeRepository.deleteById(deletedCareAnnouncementType.getId());
    }
}
