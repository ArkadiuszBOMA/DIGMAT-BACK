package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.stereotype.Component;

@Component
public class VoivodeshipMapper {

    public Voivodeship mapNewVoivodeshipDtoToEntity(NewVoivodeshipDto dto) {
        return new Voivodeship(dto.name(), dto.terytId());
    }
    public VoivodeshipDto mapEntityToVoivodeshipDto(Voivodeship entity) {
        return new VoivodeshipDto(
                entity.getId(),
                entity.getTerytId(),
                entity.getName(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()
        );
    }
}
