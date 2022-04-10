package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
