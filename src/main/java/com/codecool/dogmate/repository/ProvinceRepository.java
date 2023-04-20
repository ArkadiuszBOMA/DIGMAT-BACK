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

    @Query("SELECT DISTINCT a FROM Province a")
    List<Province> findAllBy();
    @Query("SELECT DISTINCT a FROM Province a")
    List<Province> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM Province a WHERE a.id = :id")
    Optional<Province> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM Province a WHERE a.name = :name")
    Optional<Province> findOneByName(String name);
    @Query("SELECT DISTINCT a FROM Province a WHERE a.voivodeship.id = :id")
    List<Province> findAllByVoivodeshipId(Integer id);
    @Modifying
    @Query("DELETE FROM Province a WHERE a.id = :id")
    void deleteById(Integer id);

}
