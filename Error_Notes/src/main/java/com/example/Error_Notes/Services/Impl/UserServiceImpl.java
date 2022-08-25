package com.example.Error_Notes.Services.Impl;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> lister() {
        return userRepository.findAll();
    }

    @Override
    public User creer(User user) {
        return userRepository.save(user);
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
        return "Vous avez déconnecter !";
    }

    @Override
    public User Seconnecter(User user) {
        return null;
    }

    @Override
    public String supprimer(Long id_user) {
        userRepository.deleteById(id_user);
        return "Suppression effectuée avec succés !";
    }
}
