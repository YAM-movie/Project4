package com.movieRate.movieRate.ModuleWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

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
    private String overview;
    private String imgurl;
    private Timestamp release_date;
    private int rate;
    private String lang;
    private Long vot_count;
    @OneToMany(fetch = FetchType.EAGER)
    List<Review> reviews;


}
