package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.Services.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping("/movieRate")
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
    public String UserDetailspagePage(@PathVariable Long id, Model model) {
        serviceImp.getUser(id, model);
        return "userdetiles";
    }

    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,
                             @RequestParam String password) {
        return "login";
    }

    @GetMapping("/movie/{id}")
    String getMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", serviceImp.getOneMovie(id, model));
        return "moviepage";
    }

    @GetMapping("/movies")
    List<Movie> getAllMovie(Model model) {
        return serviceImp.getAllMovies(model);
    }

    @GetMapping("/discover")
    List<AppUser> getALLUsers(Model model) {
        return serviceImp.getALLUsers(model);
    }

    @GetMapping("/getdata")
    void getdata(Model model) {
        serviceImp.getAPi();
    }

    @GetMapping("/userreviews/{id}")
    String getUserReviews(@PathVariable Long id, Model model) {
        model.addAttribute("movie", serviceImp.getUserReviews(id, model));
        return "userreviews";
    }

}

