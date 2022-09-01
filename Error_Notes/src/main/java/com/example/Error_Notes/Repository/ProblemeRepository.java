package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Solution;
import com.example.Error_Notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProblemeRepository extends JpaRepository<Probleme, Long> {


    Optional<Probleme> findByIdprobleme(Long idprobleme);
    Probleme findByTitre(String titre);
    @Query(value = "SELECT * FROM probleme WHERE probleme.titre LIKE %?1%" +
            " OR probleme.description LIKE %?1%" +
            " OR probleme.technologie LIKE %?1%" +
            " OR probleme.etat LIKE %?1%", nativeQuery = true)
    List<Probleme> findAll(String mot_cle);
}
