package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
}
