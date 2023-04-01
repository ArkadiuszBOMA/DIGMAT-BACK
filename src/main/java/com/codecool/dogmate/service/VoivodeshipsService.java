package com.codecool.dogmate.service;

import com.codecool.dogmate.controller.advice.Exceptions.VoivodeshipsNotFoundException;
import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.VoivodeshipMapper;
import com.codecool.dogmate.repository.VoivodeshipRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class VoivodeshipsService {
    private final VoivodeshipRepository voivodeshipRepository;
    private final VoivodeshipMapper voivodeshipMapper;

    public VoivodeshipsService(VoivodeshipRepository voivodeshipRepository, VoivodeshipMapper voivodeshipMapper) {
        this.voivodeshipRepository = voivodeshipRepository;
        this.voivodeshipMapper = voivodeshipMapper;
    }

    public List<VoivodeshipDto> getVoivodeships() {
        return voivodeshipRepository.findAllBy().stream()
                .sorted(Comparator.comparing(Voivodeship::getName))
                .map(voivodeshipMapper::mapEntityToVoivodeshipDto)
                .toList();
    }

    public List<VoivodeshipDto> getVoivodeships(Pageable pageable) {
        return voivodeshipRepository.findAllBy(pageable).stream()
                .sorted(Comparator.comparing(Voivodeship::getName))
                .map(voivodeshipMapper::mapEntityToVoivodeshipDto)
                .toList();
    }


    public VoivodeshipDto getVoivodeshipByVoivodeshipId(Integer id) {
        return voivodeshipRepository.findOneById(id)
                .map(voivodeshipMapper::mapEntityToVoivodeshipDto)
                .orElseThrow(() -> new VoivodeshipsNotFoundException(id));
    }

    public VoivodeshipDto createVoivodeship(NewVoivodeshipDto voivodeship) {
        Voivodeship entity = voivodeshipMapper.mapNewVoivodeshipDtoToEntity(voivodeship);
        Voivodeship savedEntity = voivodeshipRepository.save(entity);
        return voivodeshipMapper.mapEntityToVoivodeshipDto(savedEntity);
    }
}

