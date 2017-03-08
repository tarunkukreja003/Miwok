package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by tarunkukreja on 06/03/17.
 */

public class HomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_home);



        CardView cardViewNumber = (CardView) findViewById(R.id.card_view1);
        assert cardViewNumber != null;
        cardViewNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,NumbersActivity.class) ;
                startActivity(intent);

            }
        });

        CardView cardViewFamily = (CardView) (findViewById(R.id.card_view_family));
        assert cardViewFamily != null;
        cardViewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,FamilyActivity.class) ;
                startActivity(intent);

            }
        });

        CardView cardViewPhrases = (CardView) (findViewById(R.id.card_view_phrases));
        assert cardViewPhrases != null;
        cardViewPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PhrasesActivity.class) ;
                startActivity(intent);
            }
        });

        CardView cardViewColors = (CardView) (findViewById(R.id.card_view_colors));
        assert cardViewColors !=null ;
        cardViewColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,ColorsActivity.class) ;
                startActivity(intent);
            }
        });
    }
}
