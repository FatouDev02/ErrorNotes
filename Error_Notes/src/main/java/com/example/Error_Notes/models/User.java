package com.example.Error_Notes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String username;

    private String password;
    private String Adresse;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade=CascadeType.ALL)
    List<Probleme> problemes;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Commentaire> commentaires;
}
