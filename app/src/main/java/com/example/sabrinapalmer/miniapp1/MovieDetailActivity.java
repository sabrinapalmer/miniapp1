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
    private int position;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    String checkedButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;

        //get data from intent
        String title = this.getIntent().getExtras().getString("title");
        String poster = this.getIntent().getExtras().getString("poster");
        String description = this.getIntent().getExtras().getString("description");
        position = this.getIntent().getExtras().getInt("position");
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

        radioButton1 = findViewById(R.id.movie_detail_already_seen);
        radioButton2 = findViewById(R.id.movie_detail_want_to_see);
        radioButton3 = findViewById(R.id.movie_detail_do_not_like);

        submitButton = findViewById(R.id.movie_detail_submit);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent radioIntent = new Intent();

                radioIntent.putExtra("position", position);
                radioIntent.putExtra("checkedRadioButton", checkedButton);

                setResult(RESULT_OK, radioIntent);
                finish();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.movie_detail_already_seen:
                if (checked)
                    checkedButton = "Already seen";
                break;
            case R.id.movie_detail_want_to_see:
                if (checked)
                    checkedButton = "Want to see";
                break;
            case R.id.movie_detail_do_not_like:
                if (checked)
                    checkedButton = "Do not like";
                break;

        }
    }

}

