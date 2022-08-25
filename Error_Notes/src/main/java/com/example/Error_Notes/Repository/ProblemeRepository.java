package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemeRepository extends JpaRepository<Probleme, Long> {
}
