package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;



    @Override
    public String creer(User user,Long iduser) {
        Optional<User> userOptional=userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            return null;
        }
        User user1=this.userRepository.save(user);
        //User user1=userRepository.findByIduser(iduser).get();
        user1.setRole(Role.SIMPLEUSER);
        this.userRepository.save(user);
        return "Utilisateur Creer";
    }

    @Override
    public  String createAdmin(User user, Long iduser){
        Optional<User> userOptional=userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            return null;
        }
        User user1=this.userRepository.save(user);
        //User user1=userRepository.findByIduser(iduser).get();
        user1.setRole(Role.Admin);
        this.userRepository.save(user);
        return "Admin Creer";

//        User u=userRepository.findByEmail(user.getEmail()).get();
//        u.setRole(Role.Admin);
//        return  userRepository.save(user);
    }

    @Override
    public User modifier(User user, Long iduser) {
      User user1 = this.userRepository.findById(iduser).orElseThrow();
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setContact(user.getContact());
            //user1.setRole(user.getRole());
            return  userRepository.save(user1);
    }
    @Override
    public String sedeconnecter() {
        return "Vous avez été déconnecter !";
    }

    @Override
    public User Seconnecter(String email, String password) {
        Optional<User> user= userRepository.findByEmailAndPassword(email,password);
        // TTT
        if (user.isEmpty()){
            return null;
        }
        return user.get();

    }

//methode  Admin
@Override
public List<User> lister() {

    return userRepository.findAll();
}
    @Override
    public String supprimer(Long iduser) {

        userRepository.deleteById(iduser);
        return "Suppression effectuée avec succés !";
    }

    @Override
    public Object recherche(String mot_cle) {
        if (mot_cle != null) {
            List<User> retrouve = userRepository.findAll(mot_cle);
            System.out.println(retrouve);
            if (retrouve.size() != 0) {
                return retrouve;
            }else{
                return "Désolé ce mot est introuvable !!";
            }
        }
        return userRepository.findAll();
    }
}
