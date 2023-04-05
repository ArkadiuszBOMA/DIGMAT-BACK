package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Breed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    @Query("SELECT DISTINCT b FROM Breed b")
    List<Breed> findAllBy();
    @Query("SELECT DISTINCT b FROM Breed b")
    List<Breed> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT b FROM Breed b WHERE b.id = :id")
    Optional<Breed> findOneById(Integer id);
    @Query("SELECT DISTINCT b FROM Breed b WHERE b.name = :name")
    Optional<Breed> findOneByName(String id);
}
