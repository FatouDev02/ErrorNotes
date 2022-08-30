package com.example.Error_Notes.Services;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.models.User;
import com.example.Error_Notes.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1=userRepository.findByUsername(username);

        if (user1 == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(),
                new ArrayList<>());
    }
    public User save(UserDto userDto){
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        newUser.setNom(userDto.getNom());
        newUser.setPrenom(userDto.getPrenom());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole(userDto.getRole());
        newUser.setAdresse(userDto.getAdresse());
        return userRepository.save(newUser);
    }

    public User modifier(User user, Long iduser) {
        User user1 = this.userRepository.findById(iduser).orElseThrow();
        user1.setNom(user.getNom());
        user1.setPrenom(user.getPrenom());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAdresse(user.getAdresse());
        user1.setUsername(user.getUsername());
        //user1.setRole(user.getRole());
        return  userRepository.save(user1);
    }

    public List<User> lister() {
        return userRepository.findAll();
    }

    public String supprimer(Long iduser) {
        userRepository.deleteById(iduser);
        return "Suppression effectuée avec succés !";
    }


}