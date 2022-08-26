package com.example.Error_Notes.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String nom;
    private String prenom;
    private String contact;
    private String Password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role ;

    @OneToMany(mappedBy = "user")
    List<Probleme> problemes;

    @OneToMany(mappedBy = "user")
    List<Commentaire> commentaires;
}
