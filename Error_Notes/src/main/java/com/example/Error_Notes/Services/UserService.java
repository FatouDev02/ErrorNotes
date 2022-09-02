package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    //methode pour admin
    List<User> lister(Long iduser);
    ///methode pour admin
    String creerunCompte(User user);
    //methode pour admin
    User creercompteAdmin(User user, Long idUser);
    User modifier(User user, Long iduser);
    ///methode pour admin
    String supprimer(Long iduser,String email);
    User Seconnecter(String email, String password);

 PasswordEncoder passwordEncoder() ;
}
