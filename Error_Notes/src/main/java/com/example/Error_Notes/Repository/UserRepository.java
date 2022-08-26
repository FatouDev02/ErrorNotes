package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findById_user(Long id_user);
   // Optional<User> findByPassword(String password);
   //Optional<User> findUserByEmailAndPassword(String email,String password);
}


