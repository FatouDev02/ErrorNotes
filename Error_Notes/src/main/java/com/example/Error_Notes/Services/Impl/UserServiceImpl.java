package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
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
        return userRepository.findById(iduser)
                .map(u ->{
                    u.setNom(user.getNom());
                    u.setPrenom(user.getPrenom());
                    u.setContact(user.getContact());
                    u.setPassword(user.getPassword());
                    u.setEmail(user.getEmail());
                    return userRepository.save(u);
                } ).orElseThrow(() -> new RuntimeException("Cet utilisateur n'existe pas !"));
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
}