package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Etat;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProblemeControllerTest {
    @Autowired
    public ProblemeRepository problemeRepository;

    @Test
    void update() {
        Probleme problemeTest = new Probleme();
        problemeTest.setIdprobleme(1L);
        problemeTest.setTechnologie("Algoritime");
        problemeTest.setDescription("comment résoudre un algo");
        problemeTest.setTechnologie("Python");
        problemeTest.setEtat(Etat.INITIAL);

        Probleme problemeSave = problemeRepository.save(problemeTest);
        Probleme problemeModifier = problemeSave;

        problemeModifier.setTechnologie("Java");
        problemeModifier.setDescription("comment faire un algorithme de trie");
         problemeTest= problemeRepository.save(problemeModifier);

        assertNotNull(problemeModifier);
        assertNotNull(problemeModifier.getIdprobleme());
        assertEquals(problemeModifier.getTitre(), problemeSave.getTitre());
        assertEquals(problemeModifier.getDescription(), problemeSave.getDescription());
        assertEquals(problemeModifier.getTechnologie(), problemeSave.getTechnologie());
        assertEquals(problemeModifier.getEtat(), problemeSave.getEtat());

    }

    @Test
    void delete() {
        Probleme problemeSupp = new Probleme();
        problemeSupp.setIdprobleme(1L);
        problemeSupp.setTechnologie("Algoritime");
        problemeSupp.setDescription("comment résoudre un algo");
        problemeSupp.setTechnologie("Python");
        problemeSupp.setEtat(Etat.INITIAL);

        Probleme problemeSuppSave = problemeRepository.save(problemeSupp);
        assertNotNull(problemeSuppSave);
        assertNotNull(problemeSupp.getIdprobleme());
        problemeRepository.findById(problemeSupp.getIdprobleme());

    }
}