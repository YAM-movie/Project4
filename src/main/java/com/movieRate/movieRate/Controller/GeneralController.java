package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.Services.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/trending")
    String getTrending(Model model) {
        model.addAttribute("trending", serviceImp.getTrending(model));
        return "trending";
    }


    @GetMapping("/movie/{title}")
    String getMoviePage(@PathVariable String title,Model model) {
        model.addAttribute("movie", serviceImp.getMovieByTitle(title,model));
        return "moviepage";
    }

    @GetMapping("/movierate")
    String getMoviePage(double rate, Model model) {
        model.addAttribute("movie", serviceImp.getMoviesByRating(rate,model));
        return "moviepage";
    }

    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,

                             @RequestParam String password) {
        return "login";
        //Signup
    }

    @GetMapping("/movie/{n}")
    String getMoviePage(@PathVariable int n, Model model) {
        model.addAttribute("movie", serviceImp.getpage(n,model));
        return "moviepage";
    }

//
//    @Override
//    public List<Movie> getpage(int n,Model m) {
//        if(n>25) return getpage(n);
//        return movieRepo.getpage()
//    }

//    public List<Movie> getMoviesbyrating(double rate, Model m) {
//        return movieRepo.getMoviesByRate(rate);
//    }



}

