package com.example.testetecnico.repositories;

import com.example.testetecnico.entities.PlanToWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlanToWatchRepository extends JpaRepository<PlanToWatch, Long> {

    @Query("SELECT p FROM PlanToWatch p WHERE p.movieId = :movieId AND p.profileId = :profileId")
    Optional<PlanToWatch> findByProfileIdAndMovieId(Long profileId, Long movieId);
}
