package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Province;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p")
    List<Province> findAllBy();
    @Query("SELECT p FROM Province p")
    List<Province> findAllBy(Pageable pageable);
    @Query("SELECT p FROM Province p WHERE p.id = :id")
    Optional<Province> findOneById(Integer id);

}
