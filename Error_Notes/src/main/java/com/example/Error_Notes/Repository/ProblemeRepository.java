package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProblemeRepository extends JpaRepository<Probleme, Long> {




    Optional<Probleme> findByMotcle(String motcle);
}
