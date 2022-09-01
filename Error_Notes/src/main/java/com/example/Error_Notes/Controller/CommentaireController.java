package com.example.Error_Notes.Controller;
import com.example.Error_Notes.Services.CommentaireService;
import com.example.Error_Notes.Services.SolutionService;
import com.example.Error_Notes.models.Commentaire;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Solution;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/commentaire")
@Api(value = "hello", description = "Methodes  Commentaire")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;
    @Autowired
    SolutionService solutionService;
    @PostMapping("/add")
    @ApiOperation(value = "Ajouter un commentaire")
    Commentaire add(@RequestBody Commentaire commentaire){

        return commentaireService.Creer(commentaire);
    }
    @DeleteMapping("/delete/{id_Commentaire}")
    @ApiOperation(value = "Supprimer un commentaire ")
    public String delete(@PathVariable Long id_Commentaire){

        return commentaireService.Supprimer(id_Commentaire);
    }
    ///////Afficher une liste de commentaire par solution
    @GetMapping("/Affichercommparsolution/{id_solution}")
    public List<Commentaire> displayCom(@PathVariable Long id_solution){
        Solution solution=solutionService.TouverSolutionparId(id_solution);
        return  commentaireService.AfficherCommentaireParSolution(solution);

    }
}
