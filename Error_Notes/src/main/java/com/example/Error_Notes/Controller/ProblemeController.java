package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.ProblemeService;
import com.example.Error_Notes.models.Probleme;
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
    Probleme add(@RequestBody Probleme probleme){
        return problemeService.creer(probleme);
    }

    @PutMapping("/update/{id_probleme}")
    Probleme update(@RequestBody Probleme probleme, @PathVariable Long id_probleme){
        return problemeService.modifier(probleme, id_probleme);
    }

    @DeleteMapping("/delete/{id_probleme}")
    String delete(@PathVariable Long id_probleme){
        return problemeService.supprimer(id_probleme);
    }


}
