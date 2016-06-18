package com.example.temilola.flixster;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.temilola.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //display custom action bar
        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);
        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM | DISPLAY_SHOW_HOME);

        Movie movie = (Movie) getIntent().getSerializableExtra("Movie");
        ImageView image= (ImageView) findViewById(R.id.ivImage);
        RatingBar ratingBar= (RatingBar) findViewById(R.id.ratingBar);
        TextView title= (TextView)findViewById(R.id.tvTitle);
        TextView overview= (TextView) findViewById(R.id.tvOverview);
        TextView releaseDate= (TextView)findViewById(R.id.tvReleaseDate);
        TextView popularity= (TextView)findViewById(R.id.tvVotePopularity);

        //populate the data
        if (title != null) {
            title.setText(movie.getOriginalTitle());
        }
        if (overview != null) {
            overview.setText(movie.getOverview());
        }
        if (releaseDate != null) {
            releaseDate.setText(movie.getReleaseDate());
        }
        if (popularity != null) {
            popularity.setText(String.valueOf(movie.getPopularity()));
        }
        image.setImageResource(0);

        //check if in landscape or portrait
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(this).load(movie.getPosterPath()).fit().centerCrop().placeholder(R.mipmap.flixster_icon).error(R.mipmap.flixster_icon).transform(new RoundedCornersTransformation(20, 20)).into(image);
        }
        else {
            if (movie.getBackdropPath() != null) {
                Picasso.with(this).load(movie.getBackdropPath()).resize(0, 200).placeholder(R.mipmap.flixster_icon).error(R.mipmap.flixster_icon).transform(new RoundedCornersTransformation(20, 20)).into(image);
            }
        }

        //check popularity to determine ratings
        double pop = movie.getPopularity();
        if (pop < 5) {
             ratingBar.setRating((float) 1.0);
        } else if (pop < 10) {
            ratingBar.setRating((float) 2.0);
        } else if (pop < 20) {
            ratingBar.setRating((float) 3.0);
        }else if (pop < 25) {
            ratingBar.setRating((float) 4.0);
        }else if (pop >= 25) {
            ratingBar.setRating((float) 5.0);
        }

    }

    public void onClick(View v) {
        finish();
    }


}
