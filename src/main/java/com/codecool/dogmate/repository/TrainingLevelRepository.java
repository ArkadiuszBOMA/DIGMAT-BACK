package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.entity.UserType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingLevelRepository extends JpaRepository<TrainingLevel, Integer> {

    @Query("SELECT DISTINCT a FROM TrainingLevel a")
    List<TrainingLevel> findAllBy();
    @Query("SELECT DISTINCT a FROM TrainingLevel a")
    List<TrainingLevel> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM TrainingLevel a WHERE a.id = :id")
    Optional<TrainingLevel> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM TrainingLevel a WHERE a.name = :name")
    Optional<TrainingLevel> findOneByName(String name);

}
