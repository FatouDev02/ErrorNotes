package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/probleme")
@Data
public class ProblemeController {
    @Autowired
    ProblemeService problemeService;

    @GetMapping("/search/{mot_cle}")
    Object search(@PathVariable String mot_cle){
        return problemeService.recherche(mot_cle);
    }

    @PostMapping("/add")
    String add(@RequestBody Probleme probleme,Long idprobleme){
        if(this.problemeService.creer(probleme,idprobleme)==null){
            return "cet probleme existe deja";
        }
        return "probleme ajouté";
    }


    @PutMapping("/update/{idprobleme}")
    Probleme update(@RequestBody Probleme probleme, @PathVariable Long idprobleme){
        return problemeService.modifier(probleme, idprobleme);
    }


    @DeleteMapping("/delete/{idprobleme}")
    String delete(@PathVariable Long idprobleme){
        return problemeService.supprimer(idprobleme);
    }


}
