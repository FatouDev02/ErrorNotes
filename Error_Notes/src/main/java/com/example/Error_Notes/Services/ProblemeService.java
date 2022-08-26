package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Probleme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProblemeService {
    //List<Probleme> lister(String mot_cle);
    String creer(Probleme probleme, Long idprobleme);
    Probleme modifier(Probleme probleme, Long idprobleme);
    String supprimer(Long idprobleme);
    Object recherche(String mot_cle);
}
