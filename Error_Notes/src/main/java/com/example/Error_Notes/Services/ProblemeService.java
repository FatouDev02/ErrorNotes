package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Probleme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface ProblemeService {
    String creer(Probleme probleme,Long idprobleme);
    Probleme modifier(Probleme probleme, Long idprobleme);
    String supprimer(Long idprobleme);
   // Probleme RechercheP(Probleme probleme,String mot_cle);
}
