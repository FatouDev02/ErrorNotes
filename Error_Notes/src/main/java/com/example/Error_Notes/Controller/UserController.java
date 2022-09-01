package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Data
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/connexion/{email}/{password}")
    public String connexion(@PathVariable("email") String email, @PathVariable("password") String password){
        if(this.userService.Seconnecter(email,password)==null){
            return "aaa";
      }
        this.userService.Seconnecter(email,password);
        return  "vous etes connectée";

    }


    @PostMapping("/add")
    public String add(@RequestBody User user,Long iduser){
        if( this.userService.creer(user,iduser)==null){
            return "Cet utilisateur existe deja";
        }
         //this.userService.creer(user,iduser);
         return "Utilisateur ajouté";
    }
    @PostMapping("/admin/add")
    public  String admin(@RequestBody User user,Long iduser){
        if( this.userService.createAdmin(user,iduser)==null){
            return "Admin existant";
        }
        //this.userService.creer(user,iduser);
        return "Admin ajouté";

    }


    @PutMapping("/update/{id_user}")
    User update(@RequestBody User user, @PathVariable Long iduser){
        //retourner un String
        return userService.modifier(user, iduser);
    }
    //@GetMapping("/")
    String deconnect(){
        return userService.sedeconnecter();
    }

    //methode pour admin
    @GetMapping("/list")
    List<User> lister(){
        return userService.lister();
    }







//methode pour admin
    @DeleteMapping("/delete/{iduser}")
    String delete(Long iduser){
        return userService.supprimer(iduser);
    }

    //Methode pour la recherche par mot clé sur le prénom et nom
    @GetMapping("/search/{mot_cle}")
    Object search(@PathVariable String mot_cle){
        return userService.recherche(mot_cle);
    }
}
