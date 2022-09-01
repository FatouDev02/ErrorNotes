package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.CommentaireRepository;
import com.example.Error_Notes.Services.CommentaireService;
import com.example.Error_Notes.models.Commentaire;
import com.example.Error_Notes.models.Solution;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired CommentaireRepository commentaireRepository;
    @Override
    public Commentaire Creer(Commentaire commentaire) {

        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> lister() {
        return commentaireRepository.findAll();
    }

    @Override
    public String Supprimer(Long id_Commentaire) {
        commentaireRepository.deleteById(id_Commentaire);
        return"Commentaire supprimé";

    }

    @Override
    public List<Commentaire> AfficherCommentaireParSolution(Solution solution) {
       return commentaireRepository.findBySolution(solution);

    }
}
