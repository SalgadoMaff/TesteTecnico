package com.example.testetecnico.services;

import com.example.testetecnico.entities.Movie;
import com.example.testetecnico.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);

    }
}
