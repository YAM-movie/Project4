package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.Services.ServiceImp;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller

public class GeneralController {
    @Autowired
    ServiceImp serviceImp;

    @GetMapping("/")
    public String HomePage() {
        serviceImp.getAPi();

        return "home";
    }

    @GetMapping("/movie")
    public String MoviePage() {
        return "moviepage";
    }

    @GetMapping("/userdetails")
    public String UserDetailspagePage() {
        return "userdetiles";
    }


    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,
                             @RequestParam String password) {
        return "login";
    }

    @GetMapping("/movie/{id}")
    Movie getMovie(@PathVariable Long id, Model model) {
        return serviceImp.getOneMovie(id, model);
    }
}

