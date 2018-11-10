package com.example.chrishsu.moviehub.data;

public class Movie {

    private final String title;
    private final String releaseDate;
    private final String image;
    private final String reviewAvg;
    private final String overview;

    public Movie(String title, String releaseDate, String image, String reviewAvg, String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.image = image;
        this.reviewAvg = reviewAvg;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }

    public String getReviewAvg() {
        return reviewAvg;
    }

    public String getOverview() {
        return overview;
    }
}
