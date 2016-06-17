package com.example.temilola.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temilola.flixster.R;
import com.example.temilola.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by temilola on 6/15/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    //view lookup cache
    private static class ViewHolder{
        TextView Title;
        TextView Overview;
        ImageView Image;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for this position
        Movie movie = getItem(position);
        //an attempt at orientation

        //check if existing view is being used otherwise inflate the view
        ViewHolder viewHolder; //view lookup tag stored in cache
        if (convertView == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_movie,parent, false );
            viewHolder.Title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.Overview= (TextView)convertView.findViewById(R.id.tvOverview);
            viewHolder.Image= (ImageView)convertView.findViewById(R.id.ivMovieImage);
            //clear out image from last time
            viewHolder.Image.setImageResource(0);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder)convertView.getTag();
        }

        //populate the data
        viewHolder.Title.setText(movie.getOriginalTitle());
        viewHolder.Overview.setText(movie.getOverview());
        viewHolder.Image.setImageResource(0);

        //check if in landscape or portrait
        if(convertView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop().placeholder(R.mipmap.flixster_icon).error(R.mipmap.flixster_icon).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.Image);
        }
        else {
            if (movie.getBackdropPath() != null) {
                Picasso.with(getContext()).load(movie.getBackdropPath()).resize(0, 200).placeholder(R.mipmap.flixster_icon).error(R.mipmap.flixster_icon).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.Image);
            }
        }
        //return the view
        return convertView;


    }
}
