package com.codecool.dogmate.dto.province;

import java.time.LocalDateTime;

public record ProvinceDto(
        Integer id,
        String name,
        String terytId,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive,
        String voivodeship

){
}
