package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Commentaire;
import com.example.Error_Notes.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    List<Commentaire> findBySolution(Solution solution);
}
