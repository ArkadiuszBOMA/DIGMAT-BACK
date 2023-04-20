package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Breed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {

    @Query("SELECT DISTINCT a FROM Breed a")
    List<Breed> findAllBy();
    @Query("SELECT DISTINCT a FROM Breed a")
    List<Breed> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM Breed a WHERE a.id = :id")
    Optional<Breed> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM Breed a WHERE a.name = :name")
    Optional<Breed> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM Breed b WHERE b.id = :id")
    void deleteById(Integer id);
    @Query("SELECT DISTINCT a FROM Breed a WHERE a.animalTypes = :id")
    List<Breed> findAllByAnimalTypesId(Integer id);
}
