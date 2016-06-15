package com.example.temilola.flixster;

import java.util.ArrayList;

/**
 * Created by temilola on 6/15/16.
 */
public class Movies {
    public String title;
    public int rating;
    public String posterUrl;


    public Movies(String title, int rating, String posterUrl) {
        this.title = title;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }

    public static ArrayList<Movies>getFakeMovies(){
        ArrayList<Movies> movies= new ArrayList<>();

        for(int i=0; i<60; i++) {
            movies.add(new Movies("The Social Network", 75, ""));
            movies.add(new Movies("The Internship", 50, ""));
            movies.add(new Movies("The Lion King", 100, ""));
        }
        return movies;
    }

    public String toString(){
        return title + "-" + rating;
    }
}
