package com.codecool.dogmate.dto.careannouncmenttype;

import javax.validation.constraints.Size;

public record NewCareAnnouncementTypeDto(
        @Size(min = 5, max = 255, message = "Typ pomocy musi mieś długość minimalną 5 i maksymalną 255 znaków")
        String name
){
}
