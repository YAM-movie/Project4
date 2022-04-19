package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.Services.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping("/movieRate")
public class GeneralController {
    @Autowired
    ServiceImp serviceImp;

    /// home page
    @GetMapping("/")
    public String HomePage(Model model, Authentication authentication) {
        serviceImp.mostViewMovie(model);
        serviceImp.topMovie(model);
        serviceImp.newMovie(model);
        if (authentication != null) serviceImp.saveAuthenticationUser(model, authentication);
        serviceImp.getAPi();
        return "home";
    }


    // trending page
    @GetMapping("/trending")
    String getTrending(Model model, Authentication authentication) {
        serviceImp.trendingPage(model);
        serviceImp.saveAuthenticationUser(model, authentication);
        return "AllMoviePage";
    }

    /// get topMovies page
    @GetMapping("/top_movies")
    String topMovies(Model model, Authentication authentication) {
        serviceImp.saveAuthenticationUser(model, authentication);
        serviceImp.topMovie(model);
        return "TopMovies";
    }


    /// initialise ModelAttribute as AppUser
    @ModelAttribute("AppUser")
    AppUser user() {
        return new AppUser();
    }

    /// signup method
    @PostMapping("/signup")
    public String signupUser(@ModelAttribute AppUser user) {
        if (serviceImp.Signup(user)) return "redirect:/?secsSignup";
        return "redirect:/?errorSignup";

    }

    /// login page
    @GetMapping("/login")
    String login() {
        return "login";
    }

    // getMapping FOR GET nUMBER OF Page
    @GetMapping("/movies/page/{currentPage}")
    String getMoviePage(Model model, @PathVariable Long currentPage, Authentication authentication) {
        serviceImp.MoviesPage(model, currentPage);
//        serviceImp.previousPage(model,currentPage);
        serviceImp.saveAuthenticationUser(model, authentication);
        serviceImp.getPage(currentPage, model);

        return "AllMoviePage";
    }

    /// get user information by username
    @GetMapping("/user_Details/{username}")
    public String UserDetailsPage(@PathVariable String username, Model model, Authentication authentication) {
        serviceImp.getUserDetailsPage(username, model);
        serviceImp.saveAuthenticationUser(model, authentication);
        return "userDetails";
    }

    /// save edit Information about user
    @PostMapping("/user/edite")
    String saveUserInfo(@ModelAttribute AppUser user) {
        if (serviceImp.saveUserInfo(user)) return "redirect:/user_Details/" + user.getAppUserName();
        return "redirect:/user_Details/" + user.getAppUserName() + "?err";
    }

    /// get movie by title
    @GetMapping("/movie/title/{title}")
    public String getMovieData(@PathVariable String title, Model model, Authentication authentication) {
        model.addAttribute("movie", serviceImp.getMovieByTitle(title, model));
        serviceImp.saveAuthenticationUser(model, authentication);
        return "moviePage";
    }

    /// method for search about movie
    @GetMapping("/search")
    String searchBox(@RequestParam String title, Model model, Authentication authentication) {
        serviceImp.saveAuthenticationUser(model, authentication);
        if (serviceImp.searchAboutMovie(title, model)) return "AllMoviePage";
        return "redirect:/movies/page/1";
    }


}

