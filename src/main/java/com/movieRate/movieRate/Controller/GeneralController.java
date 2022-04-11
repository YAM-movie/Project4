package com.movieRate.movieRate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GeneralController {


    @GetMapping("/")
    public String HomePage() {
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
}

