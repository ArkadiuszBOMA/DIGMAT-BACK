package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.TimeUnit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeUnitRepository extends JpaRepository<TimeUnit, Integer> {

    @Query("SELECT a FROM TimeUnit a")
    List<TimeUnit> findAllBy();
    @Query("SELECT a FROM TimeUnit a")
    List<TimeUnit> findAllBy(Pageable pageable);
    @Query("SELECT a FROM TimeUnit a WHERE a.id = :id")
    Optional<TimeUnit> findOneById(Integer id);

}
