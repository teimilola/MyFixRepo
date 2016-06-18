package com.example.temilola.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by temilola on 6/15/16.
 */
public class Movie implements Serializable {
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        if(backdropPath != "null") {
            return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
        }
        return null;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    double popularity;
    int voteCount;
    double voteAvg;
    String releaseDate;
    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;


    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath= jsonObject.getString("poster_path");
        this.originalTitle= jsonObject.getString("title");
        this.overview= jsonObject.getString("overview");
        this.backdropPath=jsonObject.getString("backdrop_path");
        this.popularity= jsonObject.getDouble("popularity");
        this.releaseDate= jsonObject.getString("release_date");
        this.voteCount= jsonObject.getInt("vote_count");
        this.voteAvg= jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results= new ArrayList<>();

        for(int i=0; i< array.length(); i++){
            try {
                results.add(new Movie(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
