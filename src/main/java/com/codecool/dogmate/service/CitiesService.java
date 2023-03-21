package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.mapper.CityMapper;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.ProvinceRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .map(cityMapper::mapEntityToCityDto)
                .toList();
    }
    public List<CityDto> getCities(Pageable pageable) {
        return cityRepository.findAllBy(pageable).stream()
                .map(cityMapper::mapEntityToCityDto)
                .toList();
    }

    public CityDto getCityById(Integer id) {
        return cityRepository.findOneById(id)
                .map(cityMapper::mapEntityToCityDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CityDto createCity(NewCityDto city) {
        City entity = cityMapper.mapNewCityDtoToEntity(city);
        Province province = provinceRepository.findOneById(city.province())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setProvince(province);
        province.getCities().add(entity);
        City savedEntity = cityRepository.save(entity);
        return cityMapper.mapEntityToCityDto(savedEntity);
    }
}
