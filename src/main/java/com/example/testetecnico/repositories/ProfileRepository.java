package com.example.testetecnico.repositories;

import com.example.testetecnico.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

