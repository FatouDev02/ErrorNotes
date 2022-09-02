package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@Data

@Api(value = "hello", description = "Methodes Problemes")
@RequestMapping("/probleme")
public class ProblemeController {
    @Autowired
    ProblemeService problemeService;
//    @ApiOperation(value = "Rechercher un probleme par mot-clé ")
//    @GetMapping("/search/{mot_cle}")
//    Object search(@PathVariable String mot_cle){
//        return problemeService.recherche(mot_cle);
//    }

    @ApiOperation(value = "Ajouter un Probleme ")
    @PostMapping("/add")
    public String add(@RequestBody Probleme probleme,Long id_probleme){
        if(this.problemeService.creer(probleme,id_probleme)==null){
            return "cet probleme existe deja";
        }
        return "probleme ajouté";
    }

    @ApiOperation(value = "Modifier un probleme ")
    @PutMapping("/updatep/{idprobleme}")
    public String update(@RequestBody Probleme probleme, @PathVariable Long idprobleme){

                this.problemeService.modifier(probleme, idprobleme);
                return "modification reussi";
    }

   @ApiOperation(value = "Supprimer un probleme ")
   @DeleteMapping("/delete/{idprobleme}")
   public  String delete(@PathVariable Long idprobleme){
        this.problemeService.supprimer(idprobleme);
        return "Suppression reussi";
    }

    public Probleme displayprb(@PathVariable String titre){
        return problemeService.trouverProblemeParTitre(titre);
    }


}
