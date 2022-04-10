package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
