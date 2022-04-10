package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
