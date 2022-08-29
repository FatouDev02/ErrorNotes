package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    //methode pour admin
    List<User> lister();
    ///methode pour admin
    String creer(User user,Long iduser);
//methode pour admin
    String createAdmin(User user, Long iduser);

    User modifier(User user, Long iduser);
    String sedeconnecter();
    User Seconnecter(String email,String password);

    ///methode pour admin
    String supprimer(Long iduser);
    Object recherche(String mot_cle);

}
