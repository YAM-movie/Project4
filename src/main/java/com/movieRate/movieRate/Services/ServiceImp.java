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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImp implements Services {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    BCryptPasswordEncoder hashpassword;

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
        StringBuilder JSONString = new StringBuilder();
        {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));

                while ((line = bufferedReader.readLine()) != null) {
                    JSONString = JSONString.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Type data = new TypeToken<ArrayList<Movie>>() {
        }.getType();

        // it will convert Json type format to Gson type  object
        List<Movie> result = new Gson().fromJson(JSONString.toString().trim(), data);
//        result.stream().map(movie -> movie.getRelease_date().replace(movie.getRelease_date())
//        System.out.println(result.get(0));
//        SaveMovie(result);
//        System.out.println(result);
//        test dfsdfsfsfsdf
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

    @Override
    public List<Movie> getTrending(Model m) {
        return movieRepo.getTrending();
    }

    @Override
    public Movie getMovieByTitle(String title, Model m) {
        Movie movie = movieRepo.getMovieByTitle(title);
        return movie;
    }

    @Override
    public List<Movie> getMoviesByRating(double rate, Model m) {
        return null;
    }




    @Override
    public boolean Signup(AppUser user) {
        AppUser user1=appUserRepo.getByappUserName(user.getAppUserName());
        System.out.println(user.getAppUserName());
        if (user1 != null)  return false;
            String hashPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            AppUser NewUser = new AppUser(user.getAppUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBarth(), hashPass);
            appUserRepo.save(NewUser);
            return true;
    }

    @Override
    public List<Movie> mostViewMovie(Model model) {
        return null;
    }

    @Override
    public List<Movie> topMovie(Model model) {
        return null;
    }

    @Override
    public List<Movie> newMovie(Model model) {
        return null;
    }

    // get Page byNumber
    @Override
    public List<Movie> getPage(Long currentPage, Model m) {
        Long stopPage = currentPage * 20;
        if (currentPage > 25) return movieRepo.getPage(1L, 20L);
        return movieRepo.getPage(currentPage * 10, stopPage);
    }


}
