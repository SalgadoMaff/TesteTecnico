package com.example.testetecnico.integration.tmdb;

import com.example.testetecnico.dto.MoviePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TheMovieDbIntegrationService {

    @Value("${application.integeation.tmdb.base-url}")
    private static String baseUrl;

    @Value("${application.integeation.tmdb.api-key}")
    private static String apiKey;

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

}