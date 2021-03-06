package com.movieRate.movieRate.Services;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.ModuleWeb.Review;
import com.movieRate.movieRate.ModuleWeb.Role;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

public interface Services {
    Movie getOneMovie(Long id, Model model);
    List<Movie> getAllMovies(Model model);
    Review getOneReview(Long id ,Model model);
    //get All Reviews for one Movie by title of movie

    List<Review> getAllReviewForOneMovie(String title, Model model);

    List<AppUser> getALLUsers(Model model);
    List<Movie> getAPi();
    void saveMovie(Movie movie ,Model model);

    AppUser getUserReviews(Long id, Model m);
    List<Movie> getPage(Long currentPage,Model m);
    Movie getMovieByTitle(String title, Model m);
    List<Movie> getMoviesByRating(double rate, Model m) ;
    boolean Signup(AppUser user);
    List<Movie> mostViewMovie(Model model);
    List<Movie> topMovie(Model model);
    List<Movie> newMovie(Model model);
    AppUser getUserDetailsPage(String username,Model model);
    void saveAuthenticationUser(Model model, Authentication authentication);
    Boolean saveUserInfo(AppUser user);
    int MoviesPage(Model model,long currentPage);
    void previousPage(Model model,long currentPage);
    boolean searchAboutMovie(String title,Model model);
    void trendingPage(Model model);

    boolean favouriteMovieForUser(Model model,Authentication authentication);

    void deleteFavMovie(Long id, Authentication authentication, Model model );

    void addMovieToMyFavourite(Long id , RedirectAttributes attributes);

    void addComment( String title, Authentication p, Model m, String body, int rate);

    Role findRoleById(Long roleId);
    Role findRoleByName(String name);

    void editMovieForm(Movie movie, Model model);

    void saveMovieForEdit(Long id, Model model);

    void firstRun();
}
