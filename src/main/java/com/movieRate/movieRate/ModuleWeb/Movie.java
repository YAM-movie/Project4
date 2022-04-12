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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public List<Review> getReviews() {
        return reviews;
    }

    @Column(length = 1000)
    private String overview;
    private String imgurl;
    private Timestamp release_date;
    private double rate;
    private String lang;
    private double vot_count;
    @OneToMany(fetch = FetchType.EAGER)
    List<Review> reviews;


}
