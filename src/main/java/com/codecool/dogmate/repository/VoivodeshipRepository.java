package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {
    List<Voivodeship> findAllBy();
    List<Voivodeship> findAllBy(Pageable pageable);
    Optional<Voivodeship> findOneById(Integer id);
    Optional<Voivodeship> findOneByName(String name);
    @Modifying
    void deleteById(Integer id);
}
