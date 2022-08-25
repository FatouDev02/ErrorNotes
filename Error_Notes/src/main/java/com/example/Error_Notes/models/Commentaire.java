package com.example.Error_Notes.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Commentaire;
    private String Contenu;


    @ManyToOne
    //@JoinColumn(name = "solution_id_solution")
    private Solution solution;

    @ManyToOne
    //@JoinColumn(name = "user_id_user")
    private User user;

}
