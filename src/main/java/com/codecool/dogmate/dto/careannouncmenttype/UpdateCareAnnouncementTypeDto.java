package com.codecool.dogmate.dto.careannouncmenttype;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateCareAnnouncementTypeDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 255, message = "Typ pomocy musi mieś długość minimalną 5 i maksymalną 255 znaków")
        String name
){
}
