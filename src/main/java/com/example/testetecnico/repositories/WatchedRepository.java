package com.example.testetecnico.repositories;

import com.example.testetecnico.entities.Watched;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WatchedRepository extends JpaRepository<Watched, Long> {

    @Query("SELECT w FROM Watched w WHERE w.movieId = :movieId AND w.profileId = :profileId")
    Optional<Watched> findByProfileIdAndMovieId(Long profileId, Long movieId);
}
