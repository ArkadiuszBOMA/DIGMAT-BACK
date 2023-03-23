package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.CareAnnouncementType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareAnnouncementTypeRepository extends JpaRepository<CareAnnouncementType, Integer> {

    @Query("SELECT a FROM CareAnnouncementType a")
    List<CareAnnouncementType> findAllBy();
    @Query("SELECT a FROM CareAnnouncementType a")
    List<CareAnnouncementType> findAllBy(Pageable pageable);
    @Query("SELECT a FROM CareAnnouncementType a WHERE a.id = :id")
    Optional<CareAnnouncementType> findOneById(Integer id);

}
