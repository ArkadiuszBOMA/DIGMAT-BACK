package com.codecool.dogmate.dto.careannouncmenttype;

import javax.validation.constraints.Size;

public record NewCareAnnouncementTypeDto(
        @Size(min = 5, max = 50, message = "Typ pomocy musi mieś długość minimalną 5 i maksymalną 50 znaków")
        String name
){
}
