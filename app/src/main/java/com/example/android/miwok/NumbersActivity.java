package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer ;
    private AudioManager am ;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }

            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
                releaseMusic();

            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
                mediaPlayer.start();

        }
    };


    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMusic();
        }

    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE) ;

        final ArrayList<Word> words= new ArrayList<Word>() ;

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one)) ;
        words.add(new Word("two", "ottiko", R.drawable.number_two, R.raw.number_two)) ;
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three)) ;
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four)) ;
        words.add(new Word("five", "masookka", R.drawable.number_five, R.raw.number_five)) ;
        words.add(new Word("six", "temmoka", R.drawable.number_six, R.raw.number_six)) ;
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven)) ;
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_seven)) ;
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_eight)) ;
        words.add(new Word("ten", "na'achha", R.drawable.number_ten, R.raw.number_nine)) ;



        WordAdapter wordAdapter=new WordAdapter(this, words) ;
        ListView listView = (ListView)(findViewById(R.id.rootView));
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMusic();

                int res = am.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Word numPos = words.get(i);

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, numPos.getmMusicId());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(listener);
                }
            }


        });

    }
    //we need to override onStop() in order to completely stop whatever is running in the app when we exit the app means
    //just hit the home button ,  the app is running though in the background
    @Override
    protected void onStop() {
        super.onStop();
        releaseMusic();
    }

    private void releaseMusic() {   //if the object is finished playing the song we need to release it in order to release the memory
        //then set it to null in order to reinitialize it when a new song plays and is currently not playing any music
        if (mediaPlayer != null) {
            mediaPlayer.release();

            mediaPlayer = null;
            am.abandonAudioFocus(onAudioFocusChangeListener) ;
        }
    }


}
