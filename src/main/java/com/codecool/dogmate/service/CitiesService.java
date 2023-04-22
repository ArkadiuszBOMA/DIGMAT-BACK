package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.CityNotFoundException;
import com.codecool.dogmate.advice.Exceptions.ProvinceNotFoundException;
import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.city.NewCityDto;
import com.codecool.dogmate.dto.city.UpdateCityDto;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.mapper.AppUserMapper;
import com.codecool.dogmate.mapper.CityMapper;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.repository.AppUserRepository;
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

    private final AppUserRepository appUserRepository;
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;
    private final AppUserMapper appUserMapper;
    private final CityMapper cityMapper;



    public CitiesService(CityRepository cityRepository, ProvinceRepository provinceRepository, CityMapper cityMapper, ProvinceMapper provinceMapper, AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.cityRepository = cityRepository;
        this.provinceRepository = provinceRepository;
        this.cityMapper = cityMapper;
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
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

    public CityDto createCity(NewCityDto city) {
        City cityNew = cityMapper.mapNewCityDtoToEntity(city);
        Province province = provinceRepository.findOneById(city.province())
                .orElseThrow(() -> new ProvinceNotFoundException(city.province()));
        cityNew.setProvince(province);
        province.getCities().add(cityNew);
        City savedEntity = cityRepository.save(cityNew);
        return cityMapper.mapEntityToCityDto(savedEntity);
    }

    public void updateCity(UpdateCityDto city) {
        log.info("Zaktualizowałem dane dla id {}", city.id());
        City updateCity = cityRepository.findOneById(city.id())
                .orElseThrow(() -> new CityNotFoundException(city.id()));
        Province province = provinceRepository.findOneById(city.province())
                .orElseThrow(() -> new ProvinceNotFoundException(city.province()));
        updateCity.setName(city.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateCity.setProvince(province);
        updateCity.setDate_modify(LocalDateTime.now());
        cityRepository.save(updateCity);
    }

    public void archiveCity(Integer id) {
        City archivedCity = cityRepository.findOneById(id)
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

    public void deleteCityData(Integer id) {
        cityRepository.findOneById(id)
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
        log.info("Usunąłeś miasto o id {}", id);
        cityRepository.deleteById(id);
    }

    public List<AppUserDto> getAppUserForThisCityId(Integer id) {
        return appUserRepository.findAllByCityId(id)
                .stream()
                .map(appUserMapper::mapEntityToAppUserDto)
                .toList();
    }
}
