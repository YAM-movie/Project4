package com.movieRate.movieRate.Services;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.Movie;
import com.movieRate.movieRate.ModuleWeb.Review;
import com.movieRate.movieRate.Repository.AppUserRepo;
import com.movieRate.movieRate.Repository.MovieRepo;
import com.movieRate.movieRate.Repository.ReviewRepo;

import com.movieRate.movieRate.Security.UserDetailsImp;
import com.movieRate.movieRate.Repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.movieRate.movieRate.ModuleWeb.Role;



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
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder hashPassword;

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
    public List<Review> getAllReviewForOneMovie(String title, Model model) {
        Movie Mov = movieRepo.getMovieByTitle( title);
        model.addAttribute("reviews", Mov.getReviews());
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
        AppUser user = new AppUser("mohammed", "sadsasda", "dsad", "dfsfds", "dfada", "123");
        m.addAttribute("userinfo", user);
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
        SaveMovie(result);
        return result;
    }

    @Override
    public void saveMovie(Movie movie, Model model) {

    }

    private void SaveMovie(List<Movie> movies) {
        List<Movie> movies1 = movieRepo.findAll();
        if (movies1.size() < 100) movieRepo.saveAll(movies);
    }


    @Override
    public Movie getMovieByTitle(String title, Model model) {

        Movie movie = movieRepo.getMovieByTitle(title);
        model.addAttribute("movie",movie);

        return movie;
    }

    @Override
    public List<Movie> getMoviesByRating(double rate, Model m) {
        return null;
    }


    @Override
    public boolean Signup(AppUser user) {
        AppUser user1 = appUserRepo.getByappUserName(user.getAppUserName());
        System.out.println(user.getAppUserName());
        if (user1 != null) return false;
        String hashPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        AppUser NewUser = new AppUser(user.getAppUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBarth(), hashPass);
        appUserRepo.save(NewUser);
        return true;
    }

    @Override
    public List<Movie> mostViewMovie(Model model) {
        model.addAttribute("mostViewMovie", movieRepo.mostViewMovie());
        return movieRepo.mostViewMovie();
    }

    @Override
    public List<Movie> topMovie(Model model) {
        model.addAttribute("topMovie", movieRepo.topMovie());
        return movieRepo.topMovie();
    }

    @Override
    public List<Movie> newMovie(Model model) {
        model.addAttribute("newMovie", movieRepo.newMovie());
        return movieRepo.newMovie();
    }

    // get Page byNumber
    @Override
    public List<Movie> getPage(Long currentPage, Model model) {
        if (currentPage == 1) {
            model.addAttribute("movies", movieRepo.getPage(1L, 24L));
            return movieRepo.getPage(1L, 24L);
        }
        if (currentPage >= 25) {
            model.addAttribute("movies", movieRepo.getPage(1L, 24L));

        } else
            model.addAttribute("movies", movieRepo.getPage((currentPage * 10) - 10, (((currentPage * 10) - 10) + 23)));
        return null;
    }

    // get user information by id and save it into model.
    @Override
    public AppUser getUserDetailsPage(String username, Model model) {
        AppUser appUser = appUserRepo.getByappUserName(username);
        model.addAttribute("userDetailInfo", appUser);
        return appUser;
    }

    @Override
    public void saveAuthenticationUser(Model model, Authentication authentication) {
        if (authentication.isAuthenticated()) model.addAttribute("userLogged", authentication.getPrincipal());

    }

    @Transactional
    @Override
    public Boolean saveUserInfo(AppUser user) {
        System.out.println(user);
        AppUser userAfterEdite = appUserRepo.getByappUserName(user.getAppUserName());
        if (hashPassword.matches(user.getPassword(), userAfterEdite.getPassword())) {
            userAfterEdite.setFirstName(user.getFirstName());
            userAfterEdite.setLastName(user.getLastName());
            userAfterEdite.setEmail(user.getEmail());
            userAfterEdite.setDateOfBarth(user.getDateOfBarth());
            appUserRepo.save(userAfterEdite);
            return true;
        }
        return false;
    }

    @Override
    public int MoviesPage(Model model, long currentPage) {
        if (currentPage <= 25) model.addAttribute("moviesPage", currentPage + 1);
        else model.addAttribute("moviesPage", 1);
        if (currentPage > 1) {
            model.addAttribute("moviePreviousPage", currentPage - 1);
        } else model.addAttribute("moviePreviousPage", 1);

        return 1;
    }

    @Override
    public void previousPage(Model model, long currentPage) {
        if (currentPage > 1) {
            model.addAttribute("moviePreviousPage", currentPage - 1);
        }
        model.addAttribute("moviePreviousPage", 1);
    }

    @Override
    public boolean searchAboutMovie(String title, Model model) {
        List<Movie> moviesSearch = movieRepo.searchAboutMovie(title);
        if (moviesSearch.size() == 0) return false;
        model.addAttribute("movies", movieRepo.searchAboutMovie(title));
        System.out.println(movieRepo.searchAboutMovie(title) + "movie");
        return true;
    }

    @Override
    public void trendingPage(Model model) {
        model.addAttribute("movies", movieRepo.getTrending());
    }



    /// get User Logged and get him from dataBase  and get Favorite Movies
    @Override
    public boolean favouriteMovieForUser(Model model, Authentication authentication) {
        UserDetailsImp userLogged = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        AppUser userLogged = (AppUser) authentication.getPrincipal();
        List<Movie> movies = appUserRepo.getById(userLogged.getUser().getId()).getFavoriteMovies();
        if (movies.size() == 0) {
            model.addAttribute("Nodata", "No available Movie Found");
            return false;
        }
        model.addAttribute("FavMovie", movies);

        return true;
    }
    @Override
    public void addComment(String title, Authentication p, Model m, String body, int rate) {
            AppUser user = appUserRepo.findByappUserName(p.getName());
            Long datetime = System.currentTimeMillis();
            Movie movie = movieRepo.getMovieByTitle(title);
            Review review = new Review(rate, body, user, title);
            reviewRepo.save(review);
            movie.getReviews().add(review);
            movieRepo.save(movie);

    }


    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow();

    }

    /// get User Logged and  get movie  and get Favorite Movies and delete movie
    @Override
    public void deleteFavMovie(Long id, Authentication authentication, Model model) {
        UserDetailsImp loggedInUser = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Movie movie = movieRepo.getById(id);
        AppUser userFromDataBase =appUserRepo.getById(loggedInUser.getUser().getId());
        userFromDataBase.getFavoriteMovies().remove(movie);
        appUserRepo.save(userFromDataBase);
    }

    @Override
    public void addMovieToMyFavourite(Long id , RedirectAttributes attributes) {
        UserDetailsImp loggedInUser = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Movie movie = movieRepo.getById(id);
        AppUser userFromDataBase =appUserRepo.getById(loggedInUser.getUser().getId());
        if (userFromDataBase.getFavoriteMovies().contains(movie))
           attributes.addFlashAttribute("movieExist", "The Movie Exist");
        else {
            userFromDataBase.getFavoriteMovies().add(movie);
            appUserRepo.save(userFromDataBase);
        }
    }


}
