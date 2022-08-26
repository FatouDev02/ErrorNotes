package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Probleme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProblemeService {
    //List<Probleme> lister(String mot_cle);
    Probleme creer(Probleme probleme);
    Probleme modifier(Probleme probleme, Long id_probleme);
    String supprimer(Long id_probleme);
    Object recherche(String mot_cle);
}
