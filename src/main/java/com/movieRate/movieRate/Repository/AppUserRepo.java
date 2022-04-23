package com.movieRate.movieRate.Repository;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByappUserName(String username);
    AppUser getByappUserName(String username);
    AppUser findAppUserByEmail(String email);

    AppUser getByEmail(String email);
}
