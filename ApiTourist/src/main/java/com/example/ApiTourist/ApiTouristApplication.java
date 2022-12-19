package com.example.ApiTourist;

import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;
import com.example.ApiTourist.services.PaysService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
public class ApiTouristApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTouristApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();

	}

	@Bean
	CommandLineRunner run(PaysService paysService){
		return args -> {
			//crée des roles des linitialisation de lapp
			paysService.ajoutrole(new Role(null,"USER"));
			paysService.ajoutrole(new Role(null,"ADMIN"));

			//crée des roles des linitialisation de lapp
			paysService.ajoutercoll(new User(null,"coul","fatou","fcusername","fc@gmail.com","fcpassword",new ArrayList<>()));
			paysService.ajoutercoll(new User(null,"coul","lyd","lydusername","lyd@gmail.com","lydpassword",new ArrayList<>()));

			//attribuer un role a un user
			paysService.addroletoColl("fcusername","ADMIN");
			paysService.addroletoColl("fcusername","USER");

		};

	}
}
