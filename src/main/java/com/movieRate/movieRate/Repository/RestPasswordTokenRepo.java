package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.RestPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestPasswordTokenRepo extends JpaRepository<RestPasswordToken, Long> {
    RestPasswordToken findByConfirmationToken(String confirmationToken);

}
