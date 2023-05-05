package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.UpdateVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.ProvinceMapper;
import com.codecool.dogmate.mapper.VoivodeshipMapper;
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
public class VoivodeshipsService {
    private final VoivodeshipRepository voivodeshipRepository;
    private final ProvinceRepository provinceRepository;
    private final VoivodeshipMapper voivodeshipMapper;
    private final ProvinceMapper provinceMapper;

    public VoivodeshipsService(VoivodeshipRepository voivodeshipRepository, ProvinceRepository provinceRepository, VoivodeshipMapper voivodeshipMapper, ProvinceMapper provinceMapper) {
        this.voivodeshipRepository = voivodeshipRepository;
        this.provinceRepository = provinceRepository;
        this.voivodeshipMapper = voivodeshipMapper;
        this.provinceMapper = provinceMapper;
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
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
    }

    public VoivodeshipDto getVoivodeshipByVoivodeshipName(String name) {
        return voivodeshipRepository.findOneByName(name)
                .map(voivodeshipMapper::mapEntityToVoivodeshipDto)
                .orElseThrow(() -> new VoivodeshipNotFoundException(name));
    }

    public VoivodeshipDto createVoivodeship(NewVoivodeshipDto voivodeship) {
        log.info("Dodałeś województwo {}", voivodeship);
        Voivodeship entity = voivodeshipMapper.mapNewVoivodeshipDtoToEntity(voivodeship);
        entity.setName(voivodeship.name().trim().toUpperCase().replaceAll("( )+", " "));
        entity.setTerytId(voivodeship.terytId().trim().replaceAll("( )+", " "));
        Voivodeship savedEntity = voivodeshipRepository.save(entity);
        return voivodeshipMapper.mapEntityToVoivodeshipDto(savedEntity);
    }
    public VoivodeshipDto updateVoivodeshipData(UpdateVoivodeshipDto voivodeship) {
        log.info("Zaktualizowałem dane dla id {}", voivodeship.id());
        Voivodeship updatedVoivodeship = voivodeshipRepository.findOneById(voivodeship.id())
                .orElseThrow(() -> new VoivodeshipNotFoundException(voivodeship.id()));
        updatedVoivodeship.setName(voivodeship.name().trim().toUpperCase().replaceAll("( )+", " "));
        updatedVoivodeship.setTerytId(voivodeship.terytId().trim().replaceAll("( )+", " "));
        updatedVoivodeship.setDate_modify(LocalDateTime.now());
        voivodeshipRepository.save(updatedVoivodeship);
        return voivodeshipMapper.mapEntityToVoivodeshipDto(updatedVoivodeship);
    }

    public void archiveVoivodeshipData(Integer id) {
        Voivodeship archivedVoivodeship = voivodeshipRepository.findOneById(id)
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
        if(!archivedVoivodeship.getArchive()) {
            archivedVoivodeship.setDate_archive(LocalDateTime.now());
            archivedVoivodeship.setArchive(true);
            log.info("Zarchiwizowane dane dla id {}", id);
        } else {
            log.info("Dane już były archiwizowane;");
        }
        voivodeshipRepository.save(archivedVoivodeship);
    }

    public void deleteVoivodeshipData(Integer id) {
        voivodeshipRepository.findOneById(id)
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
        log.info("Usunąłeś województwo o id {}", id);
        voivodeshipRepository.deleteById(id);
    }

    public List<ProvinceDto> getProvincesForThisVoivodeshipById(Integer id) {
        return provinceRepository.findAllByVoivodeshipId(id)
                .stream()
                .map(provinceMapper::mapEntityToProvinceDto)
                .toList();
    }
}

