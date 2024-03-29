package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.CareAnnouncementType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareAnnouncementTypeRepository extends JpaRepository<CareAnnouncementType, Integer> {

    @Query("SELECT DISTINCT a FROM CareAnnouncementType a")
    List<CareAnnouncementType> findAllBy();
    @Query("SELECT DISTINCT a FROM CareAnnouncementType a")
    List<CareAnnouncementType> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM CareAnnouncementType a WHERE a.id = :id")
    Optional<CareAnnouncementType> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM CareAnnouncementType a WHERE a.name = :name")
    Optional<CareAnnouncementType> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM CareAnnouncementType a WHERE a.id = :id")
    void deleteById(Integer id);

}
