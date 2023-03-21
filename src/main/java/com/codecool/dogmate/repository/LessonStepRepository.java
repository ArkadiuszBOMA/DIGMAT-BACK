package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.LessonStep;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonStepRepository extends JpaRepository<LessonStep, Integer> {
    @Query("SELECT a FROM LessonStep a")
    List<LessonStep> findAllBy();
    @Query("SELECT a FROM LessonStep a")
    List<LessonStep> findAllBy(Pageable pageable);
    @Query("SELECT a FROM LessonStep a WHERE a.id = :id")
    Optional<LessonStep> findOneById(Integer id);

}
