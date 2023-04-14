package com.codecool.dogmate.service;

import com.codecool.dogmate.advice.Exceptions.VoivodeshipNotFoundException;
import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.UpdateVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import com.codecool.dogmate.mapper.VoivodeshipMapper;
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
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
    }

    public VoivodeshipDto getVoivodeshipByVoivodeshipName(String name) {
        return voivodeshipRepository.findOneByName(name)
                .map(voivodeshipMapper::mapEntityToVoivodeshipDto)
                .orElseThrow(() -> new VoivodeshipNotFoundException(name));
    }

    public VoivodeshipDto createVoivodeship(NewVoivodeshipDto voivodeship) {
        log.info("Rejestracja {}", voivodeship);
        Voivodeship entity = voivodeshipMapper.mapNewVoivodeshipDtoToEntity(voivodeship);
        Voivodeship savedEntity = voivodeshipRepository.save(entity);
        return voivodeshipMapper.mapEntityToVoivodeshipDto(savedEntity);
    }
    public void updateVoivodeshipData(UpdateVoivodeshipDto voivodeshio) {
        log.info("Zaktualizowałem dane dla id {}", voivodeshio.id());
        Voivodeship updatedVoivodeship = voivodeshipRepository.findOneById(voivodeshio.id())
                .orElseThrow(() -> new VoivodeshipNotFoundException(voivodeshio.id()));
        updatedVoivodeship.setName(voivodeshio.name().trim().toUpperCase().replaceAll("( )+", " "));
        updatedVoivodeship.setTerytId(voivodeshio.terytId().trim().replaceAll("( )+", " "));
        updatedVoivodeship.setDate_modify(LocalDateTime.now());
        voivodeshipRepository.save(updatedVoivodeship);
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
        Voivodeship deletedVoivodeship = voivodeshipRepository.findOneById(id)
                .orElseThrow(() -> new VoivodeshipNotFoundException(id));
        log.info("Usunąłeś województwo o id {}", id);
        voivodeshipRepository.deleteById(deletedVoivodeship.getId());
    }
}

