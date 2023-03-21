package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.AnimalType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {

    @Query("SELECT a FROM AnimalType a")
    List<AnimalType> findAllBy();
    @Query("SELECT a FROM AnimalType a")
    List<AnimalType> findAllBy(Pageable pageable);
    @Query("SELECT a FROM AnimalType a WHERE a.id = :id")
    Optional<AnimalType> findOneById(Integer id);
}
