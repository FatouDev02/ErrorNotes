package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Commentaire;

import java.util.List;

public interface CommentaireService {
    Commentaire Creer(Commentaire commentaire);
    List<Commentaire> lister();
    String Supprimer(Long id_Commentaire);
}
