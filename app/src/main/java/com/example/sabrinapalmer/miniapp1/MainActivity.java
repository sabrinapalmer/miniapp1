package com.example.sabrinapalmer.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        final ArrayList<Moviez> movieList = Moviez.getMoviesFromFile("movies.json", this);

        MovieAdapter adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.star_wars_list_view);
        mListView.setAdapter(adapter);


        //1. make row clickable and create intent to send to detail activity

        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

                                              @Override
                                              public void onItemClick(AdapterView<?> parent, View view,
                                                                      int position, long id){
                                                  Moviez selectedMovie = movieList.get(position);

                                                  // create my intent package
                                                  // add all the information needed for detail page
                                                  // startActivity with that intent

                                                  //explicit
                                                  // from, to
                                                  Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                                                  // put title and instruction URL
                                                  detailIntent.putExtra("position", position);
                                                  detailIntent.putExtra("title", selectedMovie.title);
                                                  detailIntent.putExtra("poster", selectedMovie.poster_url);
                                                  detailIntent.putExtra("description", selectedMovie.description);

                                                  startActivity(detailIntent);




                                              }
                                          }
        );



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // SECOND ACTIVITY IS SENDING DATA
                boolean button11 = data.getBooleanExtra("button1", false);
                boolean button22 = data.getBooleanExtra("button2", false);
                boolean button33 = data.getBooleanExtra("button3", false);
                int positt = data.getIntExtra("postoreturn",0);
                TextView hasseen = mListView.getChildAt(positt).findViewById(R.id.movie_has_seen);
                if(button11){
                    hasseen.setText("Already seen");
                }
                else if(button22){
                    hasseen.setText("Want to see");
                }
                else if(button33){
                    hasseen.setText("Do not like");
                }
                else{
                    hasseen.setText("NO");
                }

            }

        }
    }

}
