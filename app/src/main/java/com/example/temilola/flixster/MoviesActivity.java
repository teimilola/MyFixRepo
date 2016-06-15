package com.example.temilola.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        //get the actual movie
        ArrayList<Movies> movies = Movies.getFakeMovies();
        //get the listview that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        //create an array adapter
        //ArrayAdapter<Movies> adapter = new ArrayAdapter<Movies>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter adapter= new MoviesAdapter(this, movies);
        //associate the adapter with the listview
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }
    }
}


