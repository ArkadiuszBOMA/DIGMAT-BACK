package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Province;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    List<Province> findAllBy();
    List<Province> findAllBy(Pageable pageable);
    Optional<Province> findOneById(Integer id);
    Optional<Province> findOneByName(String name);
    List<Province> findAllByVoivodeshipId(Integer id);
    @Modifying
    void deleteById(Integer id);

}
