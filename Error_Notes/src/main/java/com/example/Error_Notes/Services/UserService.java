package com.example.Error_Notes.Services;

import com.example.Error_Notes.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> lister();
    User creer(User user);
    User modifier(User user, Long id_user);
    String sedeconnecter();
    User Seconnecter(User user);
    String supprimer(Long id_user);

}
