package com.example.sabrinapalmer.miniapp1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sabrinapalmer on 2/16/18.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Moviez> mMovieList;
    private LayoutInflater mInflater;


    public MovieAdapter(Context mContext, ArrayList<Moviez> mMovieList){

        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //methods
    @Override
    public int getCount(){ return mMovieList.size(); }

    @Override
    public Object getItem(int position) { return mMovieList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);

            holder = new ViewHolder();

            holder.titleTextView = convertView.findViewById(R.id.movie_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_description);
            holder.charactersTextView = convertView.findViewById(R.id.movie_characters);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_thumbnail);
            holder.hasseenTextView = convertView.findViewById(R.id.movie_has_seen);

            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView charactersTextView = holder.charactersTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView hasseenTextView = holder.hasseenTextView;

        Moviez movie = (Moviez)getItem(position);

        titleTextView.setText(movie.title);
        titleTextView.setTextSize(20);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextSize(11);
        descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));


        String char1 = movie.main_characters.get(0);
        String char2 = movie.main_characters.get(1);
        String char3 = movie.main_characters.get(2);
        String comma = ", ";
        String characters = char1 + comma + char2 + comma + char3;
        charactersTextView.setText(characters);
        charactersTextView.setTextSize(11);
        charactersTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        Picasso.with(mContext).load(movie.poster_url).into(thumbnailImageView);


        return convertView;





    }


    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView charactersTextView;
        public ImageView thumbnailImageView;
        public TextView hasseenTextView;
    }

}
