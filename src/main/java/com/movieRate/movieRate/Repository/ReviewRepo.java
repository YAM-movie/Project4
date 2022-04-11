package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
