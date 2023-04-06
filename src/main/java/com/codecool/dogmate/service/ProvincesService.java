package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.LessonStepNotFoundException;
import com.codecool.dogmate.advice.Exceptions.ProvinceNotFoundException;
import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.ProvinceMapper;
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

    private final ProvinceRepository provinceRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final ProvinceMapper provinceMapper;

    public ProvincesService(ProvinceRepository provinceRepository, VoivodeshipRepository voivodeshipRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.voivodeshipRepository = voivodeshipRepository;
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
        voivodeship.getProvinces().add(entity);
        Province savedEntity = provinceRepository.save(entity);
        return provinceMapper.mapEntityToProvinceDto(savedEntity);
    }

    public void updateProvince(ProvinceDto province) {
        log.info("Zaktualizowałem dane dla id {}", province.id());
        Province updateProvince = provinceRepository.findById(province.id())
                .orElseThrow(() -> new LessonStepNotFoundException(province.id()));
        Voivodeship voivodeship = voivodeshipRepository.findOneByName(province.voivodeship())
                .orElseThrow(() -> new VoivodeshipNotFoundException(province.voivodeship()));
        updateProvince.setName(province.name().trim().toUpperCase().replaceAll("( )+", " "));
        updateProvince.setVoivodeship(voivodeship);
        updateProvince.setDate_modify(LocalDateTime.now());
        provinceRepository.save(updateProvince);
    }

    public void archiveProvince(Integer id) {
        Province archivedProvince = provinceRepository.findById(id)
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


}

