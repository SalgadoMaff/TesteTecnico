package com.example.testetecnico.repositories;

import com.example.testetecnico.entities.Account;
import com.example.testetecnico.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.account = :account")
    Optional<Profile> findByAccount(@Param("account") Account account);


}

