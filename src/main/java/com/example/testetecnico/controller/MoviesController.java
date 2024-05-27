package com.example.testetecnico.controller;


import com.example.testetecnico.dto.MoviePageDto;
import com.example.testetecnico.integration.tmdb.TheMovieDbIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/resources/movies", produces = "application/json")
public class MoviesController {

    @Autowired
    private TheMovieDbIntegrationService theMovieDbIntegrationService;

    @GetMapping
    public MoviePageDto searchMovies(@RequestParam String query) {
        return theMovieDbIntegrationService.searchMovies(query, 1);
    }

}