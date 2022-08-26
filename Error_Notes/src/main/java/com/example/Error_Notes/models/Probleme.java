package com.example.Error_Notes.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprobleme;
    private String titre;
    private String description;
    private String technologie;
    private String methodologie;
    @Enumerated(EnumType.STRING)
    private Etat etat;


    @OneToOne
    //@JoinColumn(name = "solution_id_solution")
    private Solution solution;

    @ManyToOne
    //@JoinColumn(name = "user_id_user")
    private User user;

}
