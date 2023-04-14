package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Province;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT DISTINCT p FROM Province p")
    List<Province> findAllBy();
    @Query("SELECT DISTINCT p FROM Province p")
    List<Province> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT p FROM Province p WHERE p.id = :id")
    Optional<Province> findOneById(Integer id);
    @Query("SELECT DISTINCT p FROM Province p WHERE p.name = :name")
    Optional<Province> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM Province p WHERE p.id = :id")
    void deleteById(Integer id);

}
