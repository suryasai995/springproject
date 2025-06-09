package com.suryacode.suryaapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.suryacode.suryaapi.projectmain.userdetails.model.User;
import com.suryacode.suryaapi.projectmain.userdetails.repository.UserRepository;

@SpringBootApplication
public class SuryaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuryaapiApplication.class, args);
	}

	//  @Bean
    // public CommandLineRunner run(UserRepository userRepository) {
    //     return args -> {
    //         userRepository.save(new User("John Doe", "john@example.com"));
    //         userRepository.save(new User("Jane Smith", "jane@example.com"));
    //     };
    // }

}
