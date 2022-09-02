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
  public Object connexion(@PathVariable("email") String email, @PathVariable("password") String password){
    if(userService.Seconnecter(email,password)==null){
          return "Connexion échouée";
     }else {
        return "Bonjour  " + userService.Seconnecter(email,password).getNom();
    }
  }


    @PostMapping("/add")
    public String add(@RequestBody User user){
        return userService.creerunCompte(user);
    }
//    @PostMapping("/admin}")
//    public  String admin(@RequestBody User user){
//
//        if(userService.Seconnecter(email,password) != null && userService.Seconnecter(email,password).getRole() != null){
//            return userService.createAdmin(user);
//        }else {
//            return "Accès refusé";
//        }
//    }
      @PostMapping("/adminadd/{idSuperAdmin}")
      public  Object admin(@RequestBody User user,@PathVariable Long idSuperAdmin){


        User user1=userService.creercompteAdmin(user, idSuperAdmin);
        if(user1!=null){
            return  user1;
        }else {
            return "Impossible de créer l'admin";
        }
      }


    @PutMapping("/update/{id_user}")
    User update(@RequestBody User user, @PathVariable Long iduser){
        //retourner un String
        return userService.modifier(user, iduser);
    }


    //methode pour admin
    @GetMapping("/list/{idadmin}")
    List<User> lister(@PathVariable Long idadmin){
        return userService.lister(idadmin);

    }

//methode pour admin
    @DeleteMapping("/delete/{iduser}/{email}")
    String delete(@PathVariable Long iduser,@PathVariable String email){
        return userService.supprimer(iduser,email);
    }

    //Methode pour la recherche par mot clé sur le prénom et nom

}
