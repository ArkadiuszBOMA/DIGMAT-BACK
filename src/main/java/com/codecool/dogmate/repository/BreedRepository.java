package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Breed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    @Query("SELECT b FROM Breed b")
    List<Breed> findAllBy();
    @Query("SELECT b FROM Breed b")
    List<Breed> findAllBy(Pageable pageable);
    @Query("SELECT b FROM Breed b WHERE b.id = :id")
    Optional<Breed> findOneById(Integer id);
}
