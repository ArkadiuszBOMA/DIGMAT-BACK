package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.AppUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    @Query("SELECT a FROM AppUser a")
    List<AppUser> findAllBy();

    @Query("SELECT a FROM AppUser a")
    List<AppUser> findAllBy(Pageable pageable);

    @Query("SELECT a FROM AppUser a WHERE a.id = :id")
    Optional<AppUser> findOneById(Integer id);

    @Query("SELECT a FROM AppUser a WHERE LOWER(a.email)=LOWER(:email)")
    Optional<AppUser> findOneByEmail(String email);

    @Query("SELECT a FROM AppUser a WHERE LOWER(a.first_name)=LOWER(:name)")
    List<AppUser> findAllByName(String name);
}
