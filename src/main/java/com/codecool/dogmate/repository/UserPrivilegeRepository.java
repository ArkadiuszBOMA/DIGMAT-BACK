package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.UserPrivilege;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Integer> {

    @Query("SELECT DISTINCT a FROM UserPrivilege a")
    List<UserPrivilege> findAllBy();
    @Query("SELECT DISTINCT a FROM UserPrivilege a")
    List<UserPrivilege> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM UserPrivilege a WHERE a.id = :id")
    Optional<UserPrivilege> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM UserPrivilege a WHERE a.name = :name")
    Optional<UserPrivilege> findOneByName(String name);
    @Modifying
    @Query("DELETE FROM UserPrivilege a WHERE a.id = :id")
    void deleteById(Integer id);

}
