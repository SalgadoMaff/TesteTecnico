package com.example.testetecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@IdClass(PlanToWatch.Pk.class)
@Table(name = "profile_plan_to_watch")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanToWatch {

    @Id
    @Column(name = "profile_id")
    private Long profileId;

    @Id
    @Column(name = "movie_id")
    private Long movieId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {
        private Long profileId;
        private Long movieId;


    }
}
