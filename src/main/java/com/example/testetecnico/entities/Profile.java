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
    @JoinColumn(name = "account_id")
    private Account account;

//    @OneToMany(mappedBy = "id")
//    private List<Movie> planToWatch;
//
//    @OneToMany(mappedBy = "id")
//    private List<Movie> watchedList;

}
