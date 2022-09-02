package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void addusertest( )
    {
       User user1=new User();
       user1.setNom("AAA");
       user1.setPrenom("Prenom");
       user1.setUsername("ww");
       user1.setRole(Role.SIMPLEUSER);
       user1.setAdresse("bko");
       user1.setEmail("aa@gmail.com");
       user1.setPassword("AZErty");
       this.userRepository.save(user1);
    }

    @Test
    public void updateusertest() {
    User user1 = this.userRepository.findByUsername("ww");
        user1.setNom("Fatou");
        user1.setPrenom("CCC");
        user1.setEmail("FC@gmail");
        user1.setPassword("azerty");
        user1.setAdresse("BKO");
        user1.setUsername("Fc");
        //user1.setRole(user.getRole());
         this.userRepository.save(user1);
        // return "mise a jour validé";

    }

    @Test
   public void listerUsers() {
        List<User> userList= this.userRepository.findAll();
        for(User u:userList){
            System.out.println(u.getUsername()+"   "+ u.getEmail()+"   "+u.getPassword());
        }
        assertThat(userList.size());
    }

    @Test
    public void deleteUser() {
       int iduser=8;
      //  User user=this.userRepository.deleteById(iduser);
        boolean isExistBeforeDelete=userRepository.findByEmail("aa@gmail.com").isPresent();
        userRepository.deleteById((long) iduser);

        boolean notExistAfterDelete=userRepository.findByEmail("aa@gmail.com").isPresent();

        assertTrue(isExistBeforeDelete);
       assertFalse(notExistAfterDelete);


    }
}