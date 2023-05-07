package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.LessonStepNotFoundException;
import com.codecool.dogmate.advice.Exceptions.ProvinceNotFoundException;
import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.dto.province.UpdateProvinceDto;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.CityMapper;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.mapper.VoivodeshipMapper;
import com.codecool.dogmate.repository.CityRepository;
import com.codecool.dogmate.repository.ProvinceRepository;
import com.codecool.dogmate.repository.VoivodeshipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class ProvincesService {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final CityMapper cityMapper;
    private final ProvinceMapper provinceMapper;



    public ProvincesService(CityRepository cityRepository, ProvinceRepository provinceRepository,
                            VoivodeshipRepository voivodeshipRepository, CityMapper cityMapper, ProvinceMapper provinceMapper, VoivodeshipMapper voivodeshipMapper) {
        this.cityRepository = cityRepository;
        this.provinceRepository = provinceRepository;
        this.voivodeshipRepository = voivodeshipRepository;
        this.cityMapper = cityMapper;
        this.provinceMapper = provinceMapper;
    }

    public List<ProvinceDto> getProvinces() {
        return provinceRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Province::getName))
                .map(provinceMapper::mapEntityToProvinceDto)
                .toList();
    }

    public List<ProvinceDto> getProvinces(Pageable pageable) {
        return provinceRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(Province::getName))
                .map(provinceMapper::mapEntityToProvinceDto)
                .toList();
    }

    public ProvinceDto getProvinceById(Integer id) {
        return provinceRepository.findOneById(id)
                .map(provinceMapper::mapEntityToProvinceDto)
                .orElseThrow(() -> new ProvinceNotFoundException(id));
    }

    public ProvinceDto getProvinceById(String name) {
        return provinceRepository.findOneByName(name)
                .map(provinceMapper::mapEntityToProvinceDto)
                .orElseThrow(() -> new ProvinceNotFoundException(name));
    }

    public ProvinceDto createProvince(NewProvinceDto province) {
        Province entity = provinceMapper.mapNewProvinceDtoToEntity(province);
        Voivodeship voivodeship = voivodeshipRepository.findOneById(province.voivodeship())
                .orElseThrow(() -> new VoivodeshipNotFoundException(province.voivodeship()));
        entity.setVoivodeship(voivodeship);
        entity.setName(province.name().trim().toUpperCase().replaceAll("( )+", " "));
        voivodeship.getProvinces().add(entity);
        Province savedEntity = provinceRepository.save(entity);
        return provinceMapper.mapEntityToProvinceDto(savedEntity);
    }

    public ProvinceDto updateProvince(UpdateProvinceDto province) {
        log.info("Zaktualizowałem dane dla id {}", province.id());
        Province updateProvince = provinceRepository.findOneById(province.id())
                .orElseThrow(() -> new LessonStepNotFoundException(province.id()));
        Voivodeship voivodeship = voivodeshipRepository.findOneById(province.voivodeship())
                .orElseThrow(() -> new VoivodeshipNotFoundException(province.voivodeship()));
        updateProvince.setName(province.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateProvince.setTerytId(province.terytId());
        updateProvince.setVoivodeship(voivodeship);
        updateProvince.setDate_modify(LocalDateTime.now());
        provinceRepository.save(updateProvince);
        return provinceMapper.mapEntityToProvinceDto(updateProvince);
    }

    public void archiveProvince(Integer id) {
        Province archivedProvince = provinceRepository.findOneById(id)
                .orElseThrow(() -> new ProvinceNotFoundException(id));
        if(!archivedProvince.getArchive()) {
            archivedProvince.setDate_archive(LocalDateTime.now());
            archivedProvince.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        provinceRepository.save(archivedProvince);
    }

    public void deleteProvinceData(Integer id) {
        provinceRepository.findOneById(id)
                .orElseThrow(() -> new ProvinceNotFoundException(id));
        log.info("Usunąłeś powiat o id {}", id);
        provinceRepository.deleteById(id);
    }


    public List<CityDto> getCitiesForThisProvinceById(Integer id) {
        return cityRepository.findAllByProvinceId(id)
                .stream()
                .map(cityMapper::mapEntityToCityDto)
                .toList();

    }
}

