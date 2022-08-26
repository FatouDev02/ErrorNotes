package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probleme")
@Data
public class ProblemeController {
    @Autowired
    ProblemeService problemeService;

    @PostMapping("/add")
    String add(@RequestBody Probleme probleme,Long idprobleme){
        if(this.problemeService.creer(probleme,idprobleme)==null){
            return "cet probleme existe deja";
        }
        return "probleme ajouté";
    }


    @PutMapping("/update/{id_probleme}")
    Probleme update(@RequestBody Probleme probleme, @PathVariable Long id_probleme){
        return problemeService.modifier(probleme, id_probleme);
    }

//   @GetMapping("/search/{mot_cle}")
//    Probleme s(@RequestBody Probleme probleme,@PathVariable String motcle){
//        return problemeService.RechercheP(probleme,motcle);
//    }

    @DeleteMapping("/delete/{id_probleme}")
    String delete(@PathVariable Long id_probleme){
        return problemeService.supprimer(id_probleme);
    }


}
