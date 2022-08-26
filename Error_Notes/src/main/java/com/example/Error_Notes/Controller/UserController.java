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
/*
    @GetMapping("/connexion/{email}&{password}")
    public User connexion(@PathVariable("email") String email, @PathVariable("password") String password){
        return userService.Seconnecter(email,password);

    }
*/
    @PostMapping("/add")
    public String add(@RequestBody User user,Long id_user){
         this.userService.creer(user,id_user);
         return "Utilisateur ajouté";
    }
    @PostMapping("/admin/add")
    public  String admin(@RequestBody User user,Long id_user){
        this.userService.createAdmin(user,id_user);
        return "Admin ajouté";

    }


    @PutMapping("/update/{id_user}")
    User update(@RequestBody User user, @PathVariable Long id_user){
        //retourner un String
        return userService.modifier(user, id_user);
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
    @DeleteMapping("/delete/{id_user}")
    String delete(Long id_user){
        return userService.supprimer(id_user);
    }
}
