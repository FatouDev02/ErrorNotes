package com.example.Error_Notes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Commentaire;
    private String Contenu;

    @JsonIgnore
    @ManyToOne
    //@JoinColumn(name = "solution_id_solution")
    private Solution solution;

    @JsonIgnore
    @ManyToOne
    //@JoinColumn(name = "user_id_user")
    private User user;

}
