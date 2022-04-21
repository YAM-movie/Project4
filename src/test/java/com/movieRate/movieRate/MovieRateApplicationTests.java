package com.movieRate.movieRate;

import com.movieRate.movieRate.Repository.AppUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



import com.movieRate.movieRate.Repository.AppUserRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


	@AutoConfigureMockMvc
	@SpringBootTest
	class MovieRateApplicationTests {
		@Autowired
		AppUserRepo appController;

		@Autowired
		private MockMvc mockMvc;


		@Test
		void helloWorlTest() throws Exception {

			mockMvc.perform(MockMvcRequestBuilders.get("/"))
					.andExpect(status().isOk())
					.andExpect(content().contentType("text/html;charset=UTF-8"))
					.andExpect(view().name("home"))
					.andExpect(content().string(Matchers.containsString("Most Viewed Movie")));

		}

		@Test
		void testLoginPage() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/login"))
					.andExpect(status().isOk())
					.andExpect(content().contentType("text/html;charset=UTF-8"))
					.andExpect(view().name("login"))
					.andExpect(content().string(Matchers.containsString("Remmeber me")));

		}

	}


