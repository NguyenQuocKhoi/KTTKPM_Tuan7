package com.example.user;

import com.example.user.models.User;
import com.example.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

//	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			for (int i = 0; i < 5; i++) {
				User user = new User("name " + i);
				userRepository.save(user);
			}
		};
	}
}


