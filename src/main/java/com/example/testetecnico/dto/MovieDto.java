package com.example.testetecnico.dto;

import com.example.testetecnico.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String overview;
    private String title;

    public Movie dtoToEntity() {
        return Movie.builder()
                .id(id)
                .overview(overview)
                .title(title)
                .build();
    }
}
