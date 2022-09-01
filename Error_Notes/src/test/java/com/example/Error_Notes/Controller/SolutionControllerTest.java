package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Repository.SolutionRepository;
import com.example.Error_Notes.models.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SolutionControllerTest {
    @Autowired
    public SolutionRepository solutionRepository;

    @Test
    void add() {
        Solution solutionAdd = new Solution();
        solutionAdd.setDescription("Cette solution est la meilleure solution");
        solutionAdd.setTemps("3H00");
        solutionAdd.setRessource("ah je ne sais pas");
        Solution solutionSave = solutionRepository.save(solutionAdd);

        assertNotNull(solutionSave);
        assertEquals(solutionSave.getDescription(), solutionAdd.getDescription());
        assertEquals(solutionSave.getTemps(), solutionAdd.getTemps());
        assertEquals(solutionSave.getRessource(), solutionAdd.getRessource());
    }

    @Test
    void update() {
        Solution solutionAdd = new Solution();
        solutionAdd.setDescription("la meilleure solution est la meilleure qu'on puisse trouver sur internet");
        solutionAdd.setTemps("12H00");
        solutionAdd.setRessource("Google.com");
        Solution solutionSave = solutionRepository.save(solutionAdd);

        Solution solutionModifier = solutionSave;
        solutionModifier.setDescription("la solution a été donner par Jean le fou dit filston Sagara");
        solutionModifier.setRessource("Javapoint");
        solutionSave = solutionRepository.save(solutionModifier);

        assertNotNull(solutionModifier);
        assertEquals(solutionModifier.getDescription(), solutionSave.getDescription());
        assertEquals(solutionModifier.getTemps(), solutionSave.getTemps());
        assertEquals(solutionModifier.getRessource(), solutionSave.getRessource());

    }

    @Test
    void delete() {
        Solution solutionAdd = new Solution();
        solutionAdd.setId_solution(1L);
        solutionAdd.setDescription("la meilleure solution est la meilleure qu'on puisse trouver sur internet");
        solutionAdd.setTemps("12H00");
        solutionAdd.setRessource("Google.com");
        Solution solutionSave = solutionRepository.save(solutionAdd);
        assertNotNull(solutionSave);
        assertNotNull(solutionSave.getId_solution());
        solutionRepository.deleteById(solutionSave.getId_solution());
    }
}