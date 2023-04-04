package com.codecool.dogmate.dto.breed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record ArchiveBreedDto(
        @NotNull
        Integer id
) {
}
