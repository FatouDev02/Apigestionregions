package com.example.ApiTourist;

import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;
import com.example.ApiTourist.services.PaysService;
import com.example.ApiTourist.services.userservices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ApiTouristApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTouristApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner run(userservices userservices){
		return args -> {
			//crée des roles des linitialisation de lapp
			userservices.ajoutrole(new Role(null,"USER"));
			userservices.ajoutrole(new Role(null,"ADMIN"));

			//crée des roles des linitialisation de lapp
			userservices.ajout(new User(null,"coulfatou","fc@gmail.com","fcusername","fcpassword",new ArrayList<>()));
			userservices.ajout(new User(null,"coulmmm","lyd@gmail.com","lydusername","lydpassword",new ArrayList<>()));

			//attribuer un role a un user
//			userservices.addroletouser("fcusername","ADMIN");
//			userservices.addroletouser("fcusername","USER");

		};

	}*/
}
