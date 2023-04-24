package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {
    @Query("SELECT DISTINCT a FROM Voivodeship a")
    List<Voivodeship> findAllBy();
    @Query("SELECT DISTINCT a FROM Voivodeship a")
    List<Voivodeship> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM Voivodeship a WHERE a.id = :id")
    Optional<Voivodeship> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM Voivodeship a WHERE a.name = :name")
    Optional<Voivodeship> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM Voivodeship a WHERE a.id = :id")
    void deleteById(Integer id);
}
