package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {
    @Query("SELECT v FROM Voivodeship v")
    List<Voivodeship> findAllBy();
    @Query("SELECT v FROM Voivodeship v")
    List<Voivodeship> findAllBy(Pageable pageable);
    @Query("SELECT v FROM Voivodeship v WHERE v.id = :id")
    Optional<Voivodeship> findOneById(Integer id);
}
