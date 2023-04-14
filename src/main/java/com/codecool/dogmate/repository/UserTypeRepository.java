package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.UserType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    @Query("SELECT DISTINCT a FROM UserType a")
    List<UserType> findAllBy();
    @Query("SELECT DISTINCT a FROM UserType a")
    List<UserType> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM UserType a WHERE a.id = :id")
    Optional<UserType> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM UserType a WHERE a.name = :name")
    Optional<UserType> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM UserType a WHERE a.id = :id")
    void deleteById(Integer id);

}
