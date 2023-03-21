package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query("SELECT a FROM Animal a")
    List<Animal> findAllBy();
    @Query("SELECT a FROM Animal a")
    List<Animal> findAllBy(Pageable pageable);
    @Query("SELECT a FROM Animal a " +
            "LEFT JOIN AnimalType at on at.id = a.animalType.id " +
            "LEFT JOIN Breed b on b.animalTypes.id = at.id "+
            "WHERE a.id = :id")
    Optional<Animal> findOneById(Integer id);

}
