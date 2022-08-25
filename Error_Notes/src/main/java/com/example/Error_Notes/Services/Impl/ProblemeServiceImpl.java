package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Probleme;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProblemeServiceImpl implements ProblemeService {
    @Autowired
    ProblemeRepository problemeRepository;
    @Override
    public Probleme creer(Probleme probleme) {
        return problemeRepository.save(probleme);
    }

    @Override
    public Probleme modifier(Probleme probleme, Long id_probleme) {
        return problemeRepository.findById(id_probleme)
                .map(p -> {
                    p.setTitre(probleme.getTitre());
                    p.setDescription(probleme.getDescription());
                    p.setTechnologie(probleme.getTechnologie());
                    p.setMethodologie(probleme.getMethodologie());
                    p.setEtat(probleme.getEtat());
                    return problemeRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Ce Probleme n'existe pas !"));
    }

    @Override
    public String supprimer(Long id_probleme) {
        problemeRepository.deleteById(id_probleme);
        return "Suppression effectuée avec succés";
    }

    @Override
    public Probleme RechercheP(Probleme probleme) {
        return null;
    }
}
