package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    @Query("SELECT u FROM Movie u WHERE u.id > 440")
    List<Movie> getTrending();


    //get from 1 to 20
    @Query("SELECT u FROM Movie u WHERE u.id >= ?1 AND u.id<=?2")
    List<Movie> getPage(Long currentPage, Long stopPage);

    @Query("SELECT u FROM Movie u WHERE u.rate > ?1")
    List<Movie> getMoviesByRate(double n);

    @Query("SELECT u FROM Movie u WHERE u.id>440")
    List<Movie> mostViewMovie();

    @Query("SELECT u FROM Movie u WHERE u.rate >= 7")
    List<Movie> topMovie();

    @Query(value = "select * from movie A WHERE CAST(A.release_date AS varchar(256)) >= '2019-01-1';", nativeQuery = true)
    List<Movie> newMovie();

    Movie getMovieByTitle(String title);
    @Query(value = "SELECT u FROM Movie u WHERE u.title LIKE CONCAT('%',:title,'%')")
    List<Movie> searchAboutMovie(String title);
}
