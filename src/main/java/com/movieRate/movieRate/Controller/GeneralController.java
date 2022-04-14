package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.Services.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
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

//    @RequestMapping(value = "/movie/{id}" ,method = RequestMethod.GET)
//    String getMovie(@PathVariable Long id, Model model) {
//        model.addAttribute("movie", serviceImp.getOneMovie(id, model));
//        return "moviepage";
//    }

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


    @GetMapping("/trending")
    String getTrending(Model model) {
        model.addAttribute("trending", serviceImp.getTrending(model));
        return "trending";
    }


    @GetMapping("/movies/movie/{title}")
    String getMoviePage(@PathVariable String title,Model model) {
        model.addAttribute("movie", serviceImp.getMovieByTitle(title,model));
        return "moviepage";
    }

    @GetMapping("/movierate")
    String getMoviePage(double rate, Model model) {
        model.addAttribute("movie", serviceImp.getMoviesByRating(rate,model));
        return "moviepage";
    }

    @ModelAttribute("AppUser")
    AppUser user(){
        return new AppUser();
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute AppUser user) {
        if (serviceImp.Signup(user))return "redirect:/?secsSignup";
        return "redirect:/?errorSignup";

    }
    @GetMapping("/login")
    String login(){
        return "login";
    }

    // getMapping FOR GET nUMBER OF Page
    @GetMapping("/movie/{currentPage}")
    String getMoviePage( Model model,@PathVariable Long currentPage) {
        List<Movie> test =serviceImp.getPage(currentPage,model);
        System.out.println(test);
        model.addAttribute("movie", serviceImp.getPage(currentPage,model));
        return "moviepage";
    }





}

