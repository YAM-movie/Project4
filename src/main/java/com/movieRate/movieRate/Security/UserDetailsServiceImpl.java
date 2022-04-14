package com.movieRate.movieRate.Security;


import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.Repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AppUserRepo appUserRepo;
    UserDetailsImp userDetailsImp;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user =appUserRepo.findByappUserName(username);
        if (appUserRepo.findByappUserName(username)!=null)return new UserDetailsImp(user);
        return null;
    }
}
