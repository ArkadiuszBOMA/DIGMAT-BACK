package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.LessonStep;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonStepRepository extends JpaRepository<LessonStep, Integer> {
    @Query("SELECT DISTINCT a FROM LessonStep a")
    List<LessonStep> findAllBy();
    @Query("SELECT DISTINCT a FROM LessonStep a")
    List<LessonStep> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM LessonStep a WHERE a.id = :id")
    Optional<LessonStep> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM LessonStep a WHERE a.name = :name")
    Optional<LessonStep> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM LessonStep a WHERE a.id = :id")
    void deleteById(Integer id);

    @Query("SELECT DISTINCT a FROM LessonStep a WHERE a.lesson.id = :id")
    List<LessonStep> findAllByLessonId(Integer id);
}
