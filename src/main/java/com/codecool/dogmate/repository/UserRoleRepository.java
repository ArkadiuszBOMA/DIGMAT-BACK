package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query("SELECT DISTINCT a FROM UserRole a")
    List<UserRole> findAllBy();
    @Query("SELECT DISTINCT a FROM UserRole a")
    List<UserRole> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM UserRole a WHERE a.id = :id")
    Optional<UserRole> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM UserRole a WHERE a.name = :name")
    Optional<UserRole> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM UserRole a WHERE a.id = :id")
    void deleteById(Integer id);

}
