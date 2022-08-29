package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Etat;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.User;
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
        // Probleme probleme1=this.problemeRepository.save(probleme);
        //probleme1.setEtat(Etat.INITIAL);
        this.problemeRepository.save(probleme);
        return "Probleme creer";
    }

    @Override
    public Probleme modifier(Probleme probleme, Long idprobleme) {
        Probleme probleme1= problemeRepository.findById(idprobleme).orElseThrow();
        // probleme1.setEtat(probleme.getEtat());
        probleme1.setTitre(probleme.getTitre());
        probleme1.setEtat(probleme.getEtat());
        probleme1.setDescription(probleme.getDescription());
        probleme1.setTechnologie(probleme.getTechnologie());
        probleme1.setMethodologie(probleme.getMethodologie());
        return problemeRepository.save(probleme1);
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
//            System.out.println(retrouve);
            if (retrouve.size() != 0) {
                return retrouve;
            }else{
                return "Désolé ce mot est introuvable !!";
            }
        }
        return problemeRepository.findAll();
    }

}
