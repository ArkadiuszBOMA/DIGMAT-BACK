package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT DISTINCT a FROM City a")
    List<City> findAllBy();
    @Query("SELECT DISTINCT a FROM City a")
    List<City> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM City a WHERE a.id = :id")
    Optional<City> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM City a WHERE a.name = :name")
    Optional<City> findOneByName(String name);
    @Query("SELECT DISTINCT a FROM City a WHERE a.province.id = :id")
    List<City> findAllByProvinceId(Integer id);
    @Modifying
    void deleteById(Integer id);

}
