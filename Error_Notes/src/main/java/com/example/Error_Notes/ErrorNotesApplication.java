package com.example.Error_Notes;

import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.Services.UserService;
import com.example.Error_Notes.models.Role;
import com.example.Error_Notes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class ErrorNotesApplication implements CommandLineRunner {

@Autowired
	UserService userService;
@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ErrorNotesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User compte = userRepository.findByEmail("fatoumatacoulibaly@gmail.com");
		if (compte == null) {

			User superAdmin = new User();
			//superAdmin.setId_compte(1L);
			superAdmin.setNom("Coulibaly");
			superAdmin.setPrenom("Fatoumata");
			superAdmin.setAdresse("90916715 mali");
			superAdmin.setEmail("fatoumatacoulibaly@gmail.com");
			superAdmin.setPassword(userService.passwordEncoder().encode("fatim"));
			superAdmin.setRole(Role.SUPER);
			superAdmin.setUsername("FC");
			userRepository.save(superAdmin);
		}
		System.out.println("creation Super OK");

	}
}
