package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Data

@Api(value = "hello", description = "Methodes Users")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "Se connecter ")
    @GetMapping("/connexion/{email}/{password}")
    public String connexion(@PathVariable("email") String email, @PathVariable("password") String password){
        if(this.userService.Seconnecter(email,password)==null){
            return "Veuiller entrer un email et un mot de passe existant";
      }
        this.userService.Seconnecter(email,password);
        return  "vous etes connectée";

    }


    @ApiOperation(value = "Creer un compte utilisateur ")
    @PostMapping("/add")
    public String add(@RequestBody User user,Long iduser){
        if( this.userService.creer(user,iduser)==null){
            return "Cet utilisateur existe deja";
        }
         //this.userService.creer(user,iduser);
         return "Utilisateur ajouté";
    }
    @PostMapping("/admin/add")
    @ApiOperation(value = "creer un compte admin ")
    public  String admin(@RequestBody User user,Long iduser){
        if( this.userService.createAdmin(user,iduser)==null){
            return "Admin existant";
        }
        //this.userService.creer(user,iduser);
        return "Admin ajouté";

    }

    @ApiOperation(value = "Modifier un compte ")
    @PutMapping("/update/{iduser}")
    User update(@RequestBody User user, @PathVariable Long iduser){
        //retourner un String
        return userService.modifier(user, iduser);
    }
    //@GetMapping("/")
    public String deconnect(){
        return userService.sedeconnecter();
    }

    //methode pour admin
    @ApiOperation(value = "  Lister les utilisateurs ")

    @GetMapping("/list")
    List<User> lister(){
        return userService.lister();
    }

//methode pour
@ApiOperation(value = " Supprimer un  utilisateur ")
    @DeleteMapping("/delete/{iduser}")
    public String delete(@PathVariable Long iduser){
        this.userService.supprimer(iduser);
        return "utilisateur supprimée";
    }

    //Methode pour la recherche par mot clé sur le prénom et nom
    @GetMapping("/search/{mot_cle}")
    Object search(@PathVariable String mot_cle){
        return userService.recherche(mot_cle);
    }
}
