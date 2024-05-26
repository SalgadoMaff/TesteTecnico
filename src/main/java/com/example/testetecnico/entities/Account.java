package com.example.testetecnico.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String name;


    private Date birthDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Profile> profiles;
}