package com.codecool.dogmate.dto.city;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityDto {
    private Integer id;
    private String name;
    private LocalDateTime date_create;
    private LocalDateTime date_modify;
    private LocalDateTime date_archive;
    private Boolean archive;
    private String province;
    private Integer provinceId;
    private String voivodeship;

    public CityDto(Integer id, String name, LocalDateTime date_create, LocalDateTime date_modify, LocalDateTime date_archive, Boolean archive, String province, Integer provinceId) {
        this.id = id;
        this.name = name;
        this.date_create = date_create;
        this.date_modify = date_modify;
        this.date_archive = date_archive;
        this.archive = archive;
        this.province = province;
        this.provinceId = provinceId;
    }
}
