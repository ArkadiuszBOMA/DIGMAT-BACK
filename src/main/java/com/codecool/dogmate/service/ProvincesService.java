package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.repository.ProvinceRepository;
import com.codecool.dogmate.repository.VoivodeshipRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .map(provinceMapper::mapEntityToProvinceDto)
                .toList();
    }

    public List<ProvinceDto> getProvinces(Pageable pageable) {
        return provinceRepository.findAllBy(pageable).stream()
                .map(provinceMapper::mapEntityToProvinceDto)
                .toList();
    }

    public ProvinceDto getProvinceById(Integer id) {
        return provinceRepository.findOneById(id)
                .map(provinceMapper::mapEntityToProvinceDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ProvinceDto createProvince(NewProvinceDto province) {
        Province entity = provinceMapper.mapNewProvinceDtoToEntity(province);
        Voivodeship voivodeship = voivodeshipRepository.findOneById(province.voivodeship())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setVoivodeship(voivodeship);
        voivodeship.getProvinces().add(entity);
        Province savedEntity = provinceRepository.save(entity);
        return provinceMapper.mapEntityToProvinceDto(savedEntity);
    }


}

