package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Repository.CommentaireRepository;
import com.example.Error_Notes.models.Commentaire;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentaireControllerTest {
    @Autowired
    public CommentaireRepository commentaireRepository;

    @Test
    void add() {
        Commentaire commentaireAdd = new Commentaire();
        commentaireAdd.setId_Commentaire(1L);
        commentaireAdd.setContenu("j'arrive pas à le faire");
        Commentaire commentaireSave = commentaireRepository.save(commentaireAdd);
        assertNotNull(commentaireSave);
        assertNotNull(commentaireSave.getId_Commentaire());
    }

    @Test
    void delete() {
        Commentaire commentaireAdd = new Commentaire();
        commentaireAdd.setId_Commentaire(1L);
        commentaireAdd.setContenu("je veux avancer rapidement dans le dev");
        Commentaire commentaireSave = commentaireRepository.save(commentaireAdd);

        assertNotNull(commentaireSave);
        assertNotNull(commentaireAdd.getId_Commentaire());
        commentaireRepository.deleteById(commentaireAdd.getId_Commentaire());
    }
}