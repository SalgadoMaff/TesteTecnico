package com.example.testetecnico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private Long id;
    private String title;
    private String overview;

    @ManyToMany(mappedBy = "planToWatch")
    private Set<Profile> profilesPlanToWatch = new HashSet<>();

    @ManyToMany(mappedBy = "watched")
    private Set<Profile> profilesWatched = new HashSet<>();
}