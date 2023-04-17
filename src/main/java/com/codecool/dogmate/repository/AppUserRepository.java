package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.AppUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    List<AppUser> findAllBy();
    List<AppUser> findAllBy(Pageable pageable);
    Optional<AppUser> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM AppUser a WHERE LOWER(a.email)=LOWER(:email)")
    Optional<AppUser> findOneByEmail(String email);
    @Query("SELECT DISTINCT a FROM AppUser a WHERE LOWER(a.first_name)=LOWER(:name)")
    List<AppUser> findAllByName(String name);
    @Query("SELECT DISTINCT a FROM AppUser a WHERE LOWER(a.last_name)=LOWER(:name)")
    List<AppUser> findAllByLastName(String name);
    List<AppUser> findAllByCityId(Integer cityId);
    List<AppUser> findAllByCityName(String cityName);
    @Modifying
    void deleteById(Integer id);
}
