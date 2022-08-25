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

    @GetMapping("/list")
    List<User> lister(){
        return userService.lister();
    }

    @PostMapping("/add")
    User add(@RequestBody User user){
        return userService.creer(user);
    }


    @PutMapping("/update/{id_user}")
    User update(@RequestBody User user, @PathVariable Long id_user){
        return userService.modifier(user, id_user);
    }

    //@GetMapping("/")
    String deconnect(){
        return userService.sedeconnecter();
    }
    String connect(){
        return userService.sedeconnecter();
    }


    @DeleteMapping("/delete/{id_user}")
    String delete(Long id_user){
        return userService.supprimer(id_user);
    }
}
