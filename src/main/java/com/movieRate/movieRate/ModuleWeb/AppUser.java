package com.movieRate.movieRate.ModuleWeb;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;


@Entity
public class AppUser  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appUserName;
    private String firstName;

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getPassword() {
        return password;
    }

    private String lastName;
    private String email;
    private String DateOfBarth;
    private String password;
    @CreationTimestamp
    private Timestamp joinIn;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appUser")
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Movie> favoriteMovies;


    public Long getId() {
        return id;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser() {
    }

    public AppUser(String username, String firstName, String lastName, String email, String dateOfBarth, String password) {
        this.appUserName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        DateOfBarth = dateOfBarth;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBarth() {
        return DateOfBarth;
    }

    public void setDateOfBarth(String dateOfBarth) {
        DateOfBarth = dateOfBarth;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getJoinIn() {
        return joinIn;
    }

    public void setJoinIn(Timestamp joinIn) {
        this.joinIn = joinIn;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
}
