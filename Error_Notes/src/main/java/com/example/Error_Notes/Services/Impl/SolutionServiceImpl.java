package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Repository.SolutionRepository;
import com.example.Error_Notes.Services.SolutionService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Solution;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    ProblemeRepository proRepository;
    @Override
    public Solution creer(Solution solution) {
        Probleme p=solution.getProblemes();
        System.out.println("mon probles==================="+p.getTitre());
        return solutionRepository.save(solution);
    }
    @Override
    public Solution creerSolutionByProblem(Solution solution,Probleme p) {

        Solution s=solutionRepository.save(solution);
        p.setSolution(s);
        proRepository.save(p);
        System.out.println("mon probles==================="+p.getTitre());
        return s;
    }
//    Probleme probleme=problemeService.trouverProblemeParId(idprobleme);
//        probleme.setSolution(s);
//        problemeService.creer(probleme,idprobleme);
//        System.out.println(idprobleme+ "=====================Mon probleme===================");

    @Override
    public Solution modifier(Solution solution, Long id_solution) {
        return solutionRepository.findById(id_solution)
                .map(s ->{
                    s.setDescription(solution.getDescription());
                    s.setTemps(solution.getTemps());
                    s.setRessource(solution.getRessource());
                    return solutionRepository.save(s);
                } ).orElseThrow(() -> new RuntimeException("Cette solution n'existe pas !"));
    }

    @Override
    public String supprimer(Long id_solution) {
        solutionRepository.deleteById(id_solution);
        return "Suppression effectuée avec succés !";
    }

    @Override
    public Solution AfficherS(Probleme probleme) {
        return solutionRepository.findByProblemes(probleme);
    }

    @Override
    public Solution TouverSolutionparId(Long id_solution) {
        return solutionRepository.findById(id_solution).get();
    }

}
