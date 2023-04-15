package com.codecool.dogmate.dto.voivodeship;

import java.time.LocalDateTime;

public record VoivodeshipDto(

        Integer id,
        String terytId,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
) {
}
