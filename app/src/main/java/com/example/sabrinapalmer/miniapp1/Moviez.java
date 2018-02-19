package com.example.sabrinapalmer.miniapp1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by sabrinapalmer on 2/16/18.
 */

public class Moviez {

    public String title;
    public int episode;
    public ArrayList<String> main_characters;
    public String description;
    public String poster_url;
    public String url;


    public static ArrayList<Moviez> getMoviesFromFile(String filename, Context context){
        ArrayList<Moviez> movieList = new ArrayList<Moviez>();

        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each movie in your movie array

            for (int i = 0; i < movies.length(); i++){
                Moviez movie = new Moviez();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode = movies.getJSONObject(i).getInt("episode_number");
                JSONArray js = movies.getJSONObject(i).getJSONArray("main_characters");


                ArrayList<String> charac = new ArrayList<String>();
                if (js != null) {
                    int len = js.length();
                    for (int l=0;l<len;l++){
                        charac.add(js.get(l).toString());
                    }
                }


                movie.main_characters = charac;
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster_url = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");

                // add to arraylist
                movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }





}
