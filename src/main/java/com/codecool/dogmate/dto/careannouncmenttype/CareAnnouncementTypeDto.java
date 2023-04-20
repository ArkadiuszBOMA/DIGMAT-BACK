package com.codecool.dogmate.dto.careannouncmenttype;

import java.time.LocalDateTime;

public record CareAnnouncementTypeDto(

    Integer id,
    String name,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive
){
}
