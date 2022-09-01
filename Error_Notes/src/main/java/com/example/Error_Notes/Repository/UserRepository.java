package com.example.Error_Notes.Repository;

import com.example.Error_Notes.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
   // Optional<User> findByPassword(String password);
   Optional<User> findByEmailAndPassword(String email,String password);

    @Query(value = "SELECT * FROM user WHERE user.nom LIKE %?1%" +
            " OR user.prenom LIKE %?1%", nativeQuery = true)
    List<User> findAll(String mot_cle);
}


