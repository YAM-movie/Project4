package com.movieRate.movieRate.Services;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.ModuleWeb.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;

import java.util.List;

public interface Services {
    Movie getOneMovie(Long id, Model model);
    List<Movie> getAllMovies(Model model);
    Review getOneReview(Long id ,Model model);
    List<Review> getAllReviewForOneMovie(Long MovieId , Model model);
    AppUser getUser(Long id,Model m);
    List<AppUser> getALLUsers(Model model);
    List<Movie> getAPi();
    void saveMovie(Movie movie ,Model model);
    AppUser getUserReviews(Long id, Model m);
    List<Movie> getTrending();

    List<Movie> getTrending();

}
