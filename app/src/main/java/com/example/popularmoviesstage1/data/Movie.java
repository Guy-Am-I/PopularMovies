package com.example.popularmoviesstage1.data;

/**
 * Class to handle movie object
 */
public class Movie {

    private String title;
    private String poster_path;
    private String backdrop_path;
    private int id;
    private double user_rating;
    private String overview;
    private String releaseDate; //Make date object in future


    public Movie(String title, String poster_path, String backdrop_path, String overview, String releaseDate,
                 int id, double user_rating) {

        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.user_rating = user_rating;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(double user_rating) {
        this.user_rating = user_rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
