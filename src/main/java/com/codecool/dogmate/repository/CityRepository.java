package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findAllBy();
    List<City> findAllBy(Pageable pageable);
    Optional<City> findOneById(Integer id);
    Optional<City> findOneByName(String name);
    List<City> findAllByProvinceId(Integer id);
    @Modifying
    void deleteById(Integer id);

}
