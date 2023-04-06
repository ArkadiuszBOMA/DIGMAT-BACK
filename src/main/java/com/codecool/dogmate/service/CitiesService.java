package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.CityNotFoundException;
import com.codecool.dogmate.advice.Exceptions.ProvinceNotFoundException;
import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.mapper.CityMapper;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class CitiesService {
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;
    private final CityMapper cityMapper;
    private final ProvinceMapper provinceMapper;



    public CitiesService(CityRepository cityRepository, ProvinceRepository provinceRepository, CityMapper cityMapper, ProvinceMapper provinceMapper) {
        this.cityRepository = cityRepository;
        this.provinceRepository = provinceRepository;
        this.cityMapper = cityMapper;
        this.provinceMapper = provinceMapper;
    }

    public List<CityDto> getCities() {
        return cityRepository.findAllBy().stream()
                .sorted(Comparator.comparing(City::getName))
                .map(cityMapper::mapEntityToCityDto)
                .toList();
    }
    public List<CityDto> getCities(Pageable pageable) {
        return cityRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(City::getName))
                .map(cityMapper::mapEntityToCityDto)
                .toList();
    }

    public CityDto getCityById(Integer id) {
        return cityRepository.findOneById(id)
                .map(cityMapper::mapEntityToCityDto)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    public CityDto getCityByName(String name) {
        return cityRepository.findOneByName(name)
                .map(cityMapper::mapEntityToCityDto)
                .orElseThrow(() -> new CityNotFoundException(name));
    }

    public CityDto getCityByProvinceId(Integer id) {
        return cityRepository.findOneByProvinceId(id)
                .map(cityMapper::mapEntityToCityDto)
                .orElseThrow(() -> new ProvinceNotFoundException(id));
    }


    public CityDto createCity(NewCityDto city) {
        City cityNew = cityMapper.mapNewCityDtoToEntity(city);
        Province province = provinceRepository.findOneById(city.province())
                .orElseThrow(() -> new ProvinceNotFoundException(city.province()));
        cityNew.setProvince(province);
        province.getCities().add(cityNew);
        City savedEntity = cityRepository.save(cityNew);
        return cityMapper.mapEntityToCityDto(savedEntity);
    }

    public void updateCity(CityDto city) {
        log.info("Zaktualizowałem dane dla id {}", city.id());
        City updateCity = cityRepository.findById(city.id())
                .orElseThrow(() -> new CityNotFoundException(city.id()));
        Province province = provinceRepository.findOneByName(city.province())
                .orElseThrow(() -> new ProvinceNotFoundException(city.province()));
        updateCity.setName(city.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateCity.setProvince(province);
        updateCity.setDate_modify(LocalDateTime.now());
        cityRepository.save(updateCity);
    }

    public void archiveCity(Integer id) {
        City archivedCity = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        if(!archivedCity.getArchive()) {
            archivedCity.setDate_archive(LocalDateTime.now());
            archivedCity.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        cityRepository.save(archivedCity);
    }
}
