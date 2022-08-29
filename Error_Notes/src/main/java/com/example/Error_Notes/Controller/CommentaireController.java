package com.example.Error_Notes.Controller;
import com.example.Error_Notes.Services.CommentaireService;
import com.example.Error_Notes.models.Commentaire;
import com.example.Error_Notes.models.Probleme;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/commentaire")

@Api(value = "hello", description = "Methodes sur Commentaire")

public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;
    @PostMapping("/add")
    Commentaire add(@RequestBody Commentaire commentaire){

        return commentaireService.Creer(commentaire);
    }
    @DeleteMapping("/delete/{id_Commentaire}")
    String delete(@PathVariable Long id_Commentaire){
        return commentaireService.Supprimer(id_Commentaire);
    }
}
