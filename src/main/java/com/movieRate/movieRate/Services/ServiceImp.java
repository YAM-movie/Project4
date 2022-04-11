package com.movieRate.movieRate.Services;


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
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.common.reflect.TypeToken;

@Service
public class ServiceImp implements Services {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;


    @Override
    public Movie getOneMovie(Long id, Model model) {
        return movieRepo.getById(id);
    }

    @Override
    public List<Movie> getAllMovies(Model model) {
        return movieRepo.findAll();
    }

    @Override
    public Review getOneReview(Long id, Model model) {
        return null;
    }

    @Override
    public List<Review> getAllReviewForOneMovie(Long MovieId, Model model) {
        return null;
    }

    @Override
    public AppUser getUser(Long id) {
        return null;
    }

    @Override
    public List<AppUser> getALLUsers(Model model) {
        return null;
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
        Type quote = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        // it will convert Json type format to Gson type  object
        List<Movie> result = new Gson().fromJson(message, quote);
        SaveMovie(result);
        return result;
    }

    @Override
    public void saveMovie(Movie movie, Model model) {

    }

    private void SaveMovie(List<Movie> movies) {
        List<Movie> movies1 = movieRepo.findAll();
        if (movies1.size() < 100) {
         movieRepo.saveAll(movies);
        }


    }
}
