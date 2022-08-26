package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
//methode pour admin
    List<User> lister();
    ///methode pour admin
    String creer(User user,Long id_user);
//methode pour admin
    User createAdmin(User user, Long id_user);

    User modifier(User user, Long id_user);
    String sedeconnecter();
    //User Seconnecter(String email,String password);

    ///methode pour admin
    String supprimer(Long id_user);

}
