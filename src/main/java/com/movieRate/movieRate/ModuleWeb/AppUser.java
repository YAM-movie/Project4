package com.movieRate.movieRate.ModuleWeb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String Email;
    private Timestamp DateOfBarth;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Movie> favoriteMovies;
}
