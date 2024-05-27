package com.example.testetecnico.integration.tmdb;

import com.example.testetecnico.dto.MovieDto;
import com.example.testetecnico.dto.MoviePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TheMovieDbIntegrationService {

    @Value("${application.integration.tmdb.base-url}")
    public String baseUrl;

    @Value("${application.integration.tmdb.api-key}")
    public String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public MoviePageDto searchMovies(String query, Integer page) {
        String url = baseUrl + "/search/movie";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey);
        ResponseEntity<MoviePageDto> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), MoviePageDto.class);
        return response.getBody();
    }

    public MovieDto getMovieById(Long id) {
        String url = baseUrl + "/movie/" + id.toString();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey);
        ResponseEntity<MovieDto> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), MovieDto.class);
        return response.getBody();
    }

}