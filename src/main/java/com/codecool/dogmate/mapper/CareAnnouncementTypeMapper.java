package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.careannouncmenttype.CareAnnouncementTypeDto;
import com.codecool.dogmate.dto.careannouncmenttype.NewCareAnnouncementTypeDto;
import com.codecool.dogmate.entity.CareAnnouncementType;
import org.springframework.stereotype.Component;

@Component
public class CareAnnouncementTypeMapper {

    public CareAnnouncementType mapCareAnnouncementTypeDtoToEntity(NewCareAnnouncementTypeDto dto) {return new CareAnnouncementType(
            dto.name()
    );}

    public CareAnnouncementTypeDto mapEntityToCareAnnouncementTypeDto(CareAnnouncementType entity) {
        return new CareAnnouncementTypeDto(
                entity.getId(),
                entity.getName()
        );
    }
}
