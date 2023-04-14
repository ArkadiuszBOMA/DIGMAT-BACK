package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT DISTINCT a FROM Lesson a")
    List<Lesson> findAllBy();
    @Query("SELECT DISTINCT a FROM Lesson a")
    List<Lesson> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM Lesson a WHERE a.id = :id")
    Optional<Lesson> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM Lesson a WHERE a.name = :name")
    Optional<Lesson> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM Lesson a WHERE a.id = :id")
    void deleteById(Integer id);

}
