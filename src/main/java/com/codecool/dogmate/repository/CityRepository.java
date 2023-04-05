package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT DISTINCT c FROM City c")
    List<City> findAllBy();
    @Query("SELECT DISTINCT c FROM City c")
    List<City> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT c FROM City c WHERE c.id = :id")
    Optional<City> findOneById(Integer id);
    @Query("SELECT DISTINCT c FROM City c WHERE c.name = :name")
    Optional<City> findOneByName(String name);
    @Query("SELECT DISTINCT c FROM City c WHERE c.province.id = :id")
    Optional<City> findOneByProvinceId(Integer id);
    @Query("SELECT DISTINCT c FROM City c WHERE c.province.name = :name")
    Optional<City> findOneByProvinceName(String name);



}
