package com.example.testetecnico.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "account")
    private List<Profile> profiles;

//    public Account(AccountDTO dto) {
//        BeanUtils.copyProperties(dto,this);
//    }


}