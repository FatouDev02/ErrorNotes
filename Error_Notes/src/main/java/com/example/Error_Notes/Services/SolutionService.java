package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Solution;
import lombok.Data;
import org.springframework.stereotype.Service;


public interface SolutionService {
    Solution creer(Solution solution);
    Solution modifier(Solution solution, Long id_solution);
    String supprimer(Long id_solution);
    Solution AfficherS(Probleme probleme);
    Solution TouverSolutionparId(Long id_solution);
}
