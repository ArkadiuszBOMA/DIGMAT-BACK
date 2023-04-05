package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query("SELECT DISTINCT a FROM Animal a")
    List<Animal> findAllBy();
    @Query("SELECT DISTINCT a FROM Animal a")
    List<Animal> findAllBy(Pageable pageable);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.id = :id")
    Optional<Animal> findOneById(Integer id);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.name = :name")
    Optional<Animal> findOneByName(String name);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.breed.id = :id")
    Optional<Animal> findOneByBreedId(Integer id);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.breed.name = :name")
    Optional<Animal> findOneByBreedName(String name);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.appUser.id = :id")
    Optional<Animal> findOneByAppUserId(Integer id);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.appUser.first_name = :name")
    Optional<Animal> findOneByAppUserFirst_name(String name);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.appUser.last_name = :name")
    Optional<Animal> findOneByAppUserLast_name(String name);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.birthYear = :id")
    Optional<Animal> findOneByBirthYear(Integer id);
    @Query("SELECT DISTINCT a FROM Animal a WHERE a.gender = :name")
    Optional<Animal> findOneByGender(String name);

}
