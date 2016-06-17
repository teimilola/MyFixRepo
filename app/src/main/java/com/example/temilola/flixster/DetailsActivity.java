package com.example.temilola.flixster;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temilola.flixster.models.Movie;

public class DetailsActivity extends AppCompatActivity {
    private static class ViewHolder{
        TextView Title;
        TextView Overview;
        TextView ReleaseDate;
        ImageView Image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //display custom action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        ViewHolder viewholder;
        viewholder= new ViewHolder();

        Movie movie = (Movie) getIntent().getSerializableExtra("Movie");

        viewholder.Title = (TextView) findViewById(R.id.tvTitle);
        viewholder.Overview = (TextView) findViewById(R.id.tvOverview);
        viewholder.ReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        viewholder.Image = (ImageView)findViewById(R.id.ivMovieImage);
        //populate the data
        viewholder.Title.setText(movie.getOriginalTitle());
        viewholder.Overview.setText(movie.getOverview());
        viewholder.ReleaseDate.setText(movie.getReleaseDate());
        viewholder.Image.setImageResource(0);
        //check popuarity to determine ratings
        double popularity = movie.getPopularity();
        if (popularity < 10) {
            viewholder.Image.setImageResource(R.drawable.one_star_rating);
        } else if (popularity < 20) {
            viewholder.Image.setImageResource(R.drawable.three_star_rating);
        } else if (popularity <= 30) {
            viewholder.Image.setImageResource(R.drawable.five_star_rating);
        }
    }

    public void onClick(View v){
        finish();
    }

}
