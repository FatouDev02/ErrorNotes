package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Solution findByProblemes(Probleme probleme);
}
