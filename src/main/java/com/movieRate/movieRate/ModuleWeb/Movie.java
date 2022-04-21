package com.movieRate.movieRate.ModuleWeb;


import javax.persistence.*;
import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public Movie() {

    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Column(length = 1000)
    private String overview;
    private String imgurl;
    private String release_date;
    private double rate;
    private String lang;
    private double vot_count;
    private String trailer;
    @Column(length = 1000)
    private String movieurl;
    @OneToMany(fetch = FetchType.EAGER)
    List<Review> reviews;


    public String getMovieurl() {
        return movieurl;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }


    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public double getVot_count() {
        return vot_count;
    }

    public void setVot_count(double vot_count) {
        this.vot_count = vot_count;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
