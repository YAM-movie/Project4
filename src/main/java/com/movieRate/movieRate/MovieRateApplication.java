package com.movieRate.movieRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.movieRate.movieRate.ModuleWeb.Role;
import com.movieRate.movieRate.Repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MovieRateApplication {

	private static final Logger log = LoggerFactory.getLogger(MovieRateApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MovieRateApplication.class, args);

	}
	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository) {
		return args -> {
			log.info("Preloading " + roleRepository.save(new Role("USER")));
			log.info("Preloading " + roleRepository.save(new Role("ADMIN")));
		};
	}


}



