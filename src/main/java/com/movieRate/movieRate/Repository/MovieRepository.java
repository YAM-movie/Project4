package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
