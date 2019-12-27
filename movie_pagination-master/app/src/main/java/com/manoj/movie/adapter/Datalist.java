package com.manoj.movie.adapter;

public class Datalist {


    private String title;
    private String poster;
    private Double rating;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Datalist(String title, String poster, Double rating, Integer id) {
        this.title = title;
        this.poster = poster;
        this.rating = rating;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
