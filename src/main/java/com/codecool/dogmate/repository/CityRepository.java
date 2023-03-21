package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT c FROM City c")
    List<City> findAllBy();
    @Query("SELECT c FROM City c")
    List<City> findAllBy(Pageable pageable);
    @Query("SELECT c FROM City c " +
            "LEFT JOIN Province p on p.id = c.province.id " +
            "LEFT JOIN Voivodeship v on v.id = p.voivodeship.id " +
            "WHERE c.id = :id")
    Optional<City> findOneById(Integer id);

}
