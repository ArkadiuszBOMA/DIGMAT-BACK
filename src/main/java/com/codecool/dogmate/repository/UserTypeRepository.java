package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.UserType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    @Query("SELECT a FROM UserType a")
    List<UserType> findAllBy();
    @Query("SELECT a FROM UserType a")
    List<UserType> findAllBy(Pageable pageable);
    @Query("SELECT a FROM UserType a " +
            "LEFT JOIN AppUser b on a.id = b.userType.id "+
            "WHERE a.id = :id")
    Optional<UserType> findOneById(Integer id);

}
