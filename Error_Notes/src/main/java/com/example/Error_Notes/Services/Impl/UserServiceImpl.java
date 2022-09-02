package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.Probleme;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String creerunCompte(User user) {
        //  Optional<User> userOptional=userRepository.findByEmail(user.getEmail());
        User mail = userRepository.findByEmail(user.getEmail());
        User username = userRepository.findByUsername(user.getUsername());
        if (mail != null) {
            return "Un compte est déjà lier à cet Email";
        } else {
            if (username != null) {
                return "Cet nom d'utilisateur existe déjà";
            } else {
                user.setRole(Role.SIMPLEUSER);
                System.out.println("============================" + user.getRole());
                this.userRepository.save(user);
                return "Utilisateur Creer";
            }
        }
    }

//        @Override
//        public String createAdmin (User user){
//            User userAcreeMail = userRepository.findByEmail(user.getEmail());
//            User userAcreeUserName = userRepository.findByUsername(user.getUsername());
//
//            if (userAcreeMail != null) {
//                return "Cet mail existe déjà";
//            }else {
//                if (userAcreeUserName != null){
//                    return "Cet nom utilisateur existe déjà";
//                }else {
//
//                    user.setRole(Role.Admin);
//                    userRepository.save(user);
//
//                    return "Admin Creer";
//
//                }
//            }
//        }

    @Override
    public User creercompteAdmin(User user, Long iduser) {
        //pour tout d'abord récupérer le compte qui crée l'admin via son id
        //System.out.println(user.getEmail());
        try{
            User superAdmin=userRepository.findById(iduser).get();

            //ser user1super=userRepository.findByEmail(user.getEmail());
            if(superAdmin.getRole() == Role.SUPER){
                user.setRole(Role.Admin);
                user.setPassword(passwordEncoder().encode(user.getPassword()));
                System.out.println("userAdmin Crée");
                return userRepository.save(user);
            }else {
                System.out.println("Action impossible");
                return null;
            }
        }catch (Exception e){
            return null;
        }


    }

    @Override
        public User modifier (User user, Long iduser){
            User user1 = this.userRepository.findById(iduser).orElseThrow();
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setAdresse(user.getAdresse());
            user1.setUsername(user.getUsername());
            //user1.setRole(user.getRole());
            return userRepository.save(user1);
        }
//    @Override
//    public String sedeconnecter() {
//        return "Vous avez été déconnecter !";
//    }

        @Override
        public User Seconnecter (String email, String password){

            User user = userRepository.findByEmailAndPassword(email, password);
            if (user != null) {
                return user;
            } else {
                return null;
            }
        }

//methode  Admin
        @Override
        public List<User> lister (Long iduser) {
            User user= userRepository.findById(iduser).get();
            if(user.getRole()== Role.SUPER || user.getRole()== Role.Admin ){

                return userRepository.findAll();

            }
            return null;
        }
        @Override
        public String supprimer (Long iduser,String email){
        User useradmin = userRepository.findByEmail(email);
        if(useradmin.getRole()== Role.SUPER || useradmin.getRole()== Role.Admin ){
            userRepository.deleteById(iduser);
            return "Utilisateur Supprimé ";
        }
        else {
            return"vous ne pouvez executer cette action";
        }


        }

//    @Override
//    public Object recherche(String mot_cle) {
//        if (mot_cle != null) {
//            List<User> retrouve = userRepository.findAll(mot_cle);
//            System.out.println(retrouve);
//            if (retrouve.size() != 0) {
//                return retrouve;
//            }else{
//                return "Désolé ce mot est introuvable !!";
//            }
//        }
//        return userRepository.findAll();
//    }


}