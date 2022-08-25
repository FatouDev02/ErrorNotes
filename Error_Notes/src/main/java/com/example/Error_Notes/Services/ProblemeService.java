package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Probleme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface ProblemeService {
    Probleme creer(Probleme probleme);
    Probleme modifier(Probleme probleme, Long id_probleme);
    String supprimer(Long id_probleme);
}
