package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.LessonStep;
import com.codecool.dogmate.entity.LessonsAnimal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonAnimalRepository extends JpaRepository<LessonsAnimal, Integer> {
    @Query("SELECT DISTINCT a FROM LessonsAnimal a")
    List<LessonsAnimal> findAllBy();
    @Query("SELECT DISTINCT a FROM LessonsAnimal a")
    List<LessonsAnimal> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM LessonsAnimal a WHERE a.id = :id")
    Optional<LessonsAnimal> findOneById(Integer id);

}
