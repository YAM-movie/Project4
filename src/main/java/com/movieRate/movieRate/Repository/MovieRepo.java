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
}
