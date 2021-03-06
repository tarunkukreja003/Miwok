/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000 ;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.SplashTheme);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class) ;
                startActivity(homeIntent);

            }
        }, SPLASH_TIME) ;
//
//
//
//        CardView cardViewNumber = (CardView) findViewById(R.id.card_view1);
//        assert cardViewNumber != null;
//        cardViewNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,NumbersActivity.class) ;
//                startActivity(intent);
//
//            }
//        });
//
//        CardView cardViewFamily = (CardView) (findViewById(R.id.card_view_family));
//        assert cardViewFamily != null;
//        cardViewFamily.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,FamilyActivity.class) ;
//                startActivity(intent);
//
//            }
//        });
//
//        CardView cardViewPhrases = (CardView) (findViewById(R.id.card_view_phrases));
//        assert cardViewPhrases != null;
//        cardViewPhrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,PhrasesActivity.class) ;
//                startActivity(intent);
//            }
//        });
//
//        CardView cardViewColors = (CardView) (findViewById(R.id.card_view_colors));
//        assert cardViewColors !=null ;
//        cardViewColors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this,ColorsActivity.class) ;
//                startActivity(intent);
//            }
//        });
  }



    }

