package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;



    @Override
    public String creer(User user,Long id_user) {
       /* Optional<User> userOptional=userRepository.findById_user(user.getId_user());
        if (userOptional.isPresent()){
            return null;
        }
        User user1=userRepository.findById_user(id_user).get();
        user1.setRole(Role.SIMPLEUSER);*/
        this.userRepository.save(user);
        return "Utilisateur Creer ";
    }

    @Override
    public  User createAdmin(User user, Long id_user){
        /*User u=userRepository.findById_user(id_user).get();
        u.setRole(Role.Admin);*/
        return  userRepository.save(user);
    }

    @Override
    public User modifier(User user, Long id_user) {
        return userRepository.findById(id_user)
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

   /* @Override
    public User Seconnecter(String email, String password) {
        Optional<User> user= userRepository.findUserByEmailAndPassword(email,password);
        // TTT
        if (user.isEmpty()){
            return null;
        }
        return user.get();

    }*/

//methode  Admin
@Override
public List<User> lister() {

    return userRepository.findAll();
}
    @Override
    public String supprimer(Long id_user) {
        userRepository.deleteById(id_user);
        return "Suppression effectuée avec succés !";
    }
}
