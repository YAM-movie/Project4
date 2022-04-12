package com.movieRate.movieRate.Services;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.ModuleWeb.Review;
import com.movieRate.movieRate.Repository.AppUserRepo;
import com.movieRate.movieRate.Repository.MovieRepo;
import com.movieRate.movieRate.Repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImp implements Services {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;


    //get Movie by Movie id

    @Override
    public Movie getOneMovie(Long id, Model model) {
        return movieRepo.getById(id);
    }

    //get All Movies
    @Override
    public List<Movie> getAllMovies(Model model) {
        return movieRepo.findAll();
    }

    //get One Review by Review id
    @Override
    public Review getOneReview(Long id, Model model) {
        return reviewRepo.getById(id);
    }

    //get All Reviews for one Movie by id of movie
    @Override
    public List<Review> getAllReviewForOneMovie(Long MovieId, Model model) {
        Movie Mov = movieRepo.getById(MovieId);
        return Mov.getReviews();

    }

    //get All user Reviews by id of user
    @Override
    public AppUser getUserReviews(Long id, Model m) {
        AppUser user = appUserRepo.getById(id);
        return (AppUser) user.getReviews();
    }

    //get user by id of user
    @Override
    public AppUser getUser(Long id, Model m) {
        AppUser user = appUserRepo.getById(id);
        return user;
    }
    //get All users by id of user

    @Override
    public List<AppUser> getALLUsers(Model model) {

        return appUserRepo.findAll();
    }

    @Override
    public List<Movie> getAPi() {

        File file = new File("Api.json");
        BufferedReader bufferedReader;
        String line;
        String message = new String();
        {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));

                while ((line = bufferedReader.readLine()) != null) {
                    message += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Type data = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        // it will convert Json type format to Gson type  object

        List<Movie> result = new Gson().fromJson(message, data);
        SaveMovie(result);

        return result;
    }

    @Override
    public void saveMovie(Movie movie, Model model) {

    }

    @Override
    public List<Movie> getTrending() {
        return movieRepo.getTrending();
    }

    private void SaveMovieApi(List<Movie> movies) {
        List<Movie> movies1 = movieRepo.findAll();
        if (movies1.size() < 100) {
            movieRepo.saveAll(movies);
        }


    }

    @Override
    public List<Movie> getTrending() {
        return movieRepo.getTrending();
    }
}

