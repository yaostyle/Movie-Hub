package com.example.chrishsu.moviehub.data;

public class Movie {

    private String title;
    private String releaseDate;
    private String image;
    private String reviewAvg;
    private String overview;

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_REVIEW_AVG = "EXTRA_REVIEW_AVG";
    public static final String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReviewAvg(String reviewAvg) {
        this.reviewAvg = reviewAvg;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
