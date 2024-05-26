package com.example.testetecnico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Account account;

//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
//    private List<Movie> planToWatch;
//
//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
//    private List<Movie> watchedList;

}
