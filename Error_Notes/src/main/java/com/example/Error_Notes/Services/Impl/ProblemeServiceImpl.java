package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Etat;
import com.example.Error_Notes.models.Probleme;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProblemeServiceImpl implements ProblemeService {
    @Autowired
    ProblemeRepository problemeRepository;


    @Override
    public String creer(Probleme probleme, Long idprobleme) {
        Optional<Probleme> problemeOptional=problemeRepository.findByIdprobleme(probleme.getIdprobleme());
        if(problemeOptional.isPresent()){
            return null;
        }
        Probleme probleme1=this.problemeRepository.save(probleme);
        probleme1.setEtat(Etat.INITIAL);
        this.problemeRepository.save(probleme);
        return "Probleme creer";
    }

    @Override
    public Probleme modifier(Probleme probleme, Long idprobleme) {
        return problemeRepository.findById(idprobleme)
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
    public String supprimer(Long idprobleme) {
        problemeRepository.deleteById(idprobleme);
        return "Suppression effectuée avec succés";
    }

    @Override
    public Object recherche(String mot_cle) {
        if (mot_cle != null) {
            List<Probleme> retrouve = problemeRepository.findAll(mot_cle);
            System.out.println(retrouve);
            if (retrouve.size() != 0) {
                return retrouve;
            }else{
                return "Désolé ce mot est introuvable !!";
            }
        }
        return problemeRepository.findAll();
    }

}
