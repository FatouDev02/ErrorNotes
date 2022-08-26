package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemeRepository extends JpaRepository<Probleme, Long> {

    @Query(value = "SELECT * FROM probleme WHERE probleme.titre LIKE %?1%"
            + " OR probleme.description LIKE %?1%"
            + " OR probleme.technologie LIKE %?1%"
            + " OR probleme.methodologie LIKE %?1%"
            + " OR probleme.etat LIKE %?1%", nativeQuery = true)
    public List<Probleme> findAll(String mot_cle);
}
