package com.example.Error_Notes.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solution;
    private String description;
    private String temps;
    private String ressource;

    @OneToOne
    private Probleme problemes;

    @OneToMany(mappedBy = "solution")
    List<Commentaire> commentaires;

}
