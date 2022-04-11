package com.movieRate.movieRate;

import com.movieRate.movieRate.Services.ServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRateApplication.class, args);
		ServiceImp serviceImp = new ServiceImp();
		serviceImp.getAPi();
	}

}



