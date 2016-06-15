package com.example.temilola.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.temilola.flixster.adapters.MovieArrayAdapter;
import com.example.temilola.flixster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        lvItems= (ListView)findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter= new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        String url= "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client= new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJSONResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        /*//get the actual movie
        ArrayList<Movies> movies = Movies.getFakeMovies();
        //get the listview that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        //create an array adapter
        //ArrayAdapter<Movies> adapter = new ArrayAdapter<Movies>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter adapter= new MoviesAdapter(this, movies);
        //associate the adapter with the listview
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }*/
    }
}


