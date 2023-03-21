package com.codecool.dogmate.dto.voivodeship;

import com.codecool.dogmate.dto.province.ProvinceDto;

import java.time.LocalDateTime;
import java.util.List;

public record VoivodeshipDto(

        Integer id,
        String terytId,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive,
        List<ProvinceDto> provinces
) {
}
