package com.example.sabrinapalmer.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sabrinapalmer.miniapp1.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView titletext;
    private ImageView posterimage;
    private TextView descriptiontext;
    private Button submitButton;
    private int passedpos;

    private boolean button1Selected;
    private boolean button2Selected;
    private boolean button3Selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;

        //get data from intent
        String title = this.getIntent().getExtras().getString("title");
        String poster = this.getIntent().getExtras().getString("poster");
        String description = this.getIntent().getExtras().getString("description");
        passedpos = this.getIntent().getExtras().getInt("position");
        //title
        setTitle(title);
        titletext = (TextView)findViewById(R.id.movie_detail_title);
        titletext.setText(title);

        //poster
        posterimage = (ImageView)findViewById(R.id.movie_detail_poster);
        Picasso.with(mContext).load(poster).into(posterimage);


        //description
        descriptiontext = (TextView)findViewById(R.id.movie_detail_description);
        descriptiontext.setText(description);

        submitButton = findViewById(R.id.movie_detail_submit);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent radioIntent = new Intent();
                radioIntent.putExtra("button1", button1Selected);
                radioIntent.putExtra("button2", button2Selected);
                radioIntent.putExtra("button3", button3Selected);
                radioIntent.putExtra("postoreturn", passedpos);
                setResult(RESULT_OK, radioIntent);
                finish();
            }
        });


    }

    public void button1Selected(View view) { button1Selected = ((RadioButton)view).isSelected();}

    public void button2Selected(View view) { button2Selected = ((RadioButton)view).isSelected();}

    public void button3Selected(View view) { button3Selected = ((RadioButton)view).isSelected();}

}

