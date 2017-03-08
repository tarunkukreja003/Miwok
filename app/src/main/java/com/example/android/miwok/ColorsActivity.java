package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private static final String LOG_TAG = ColorsActivity.class.getSimpleName() ;

    private MediaPlayer music ;
    private AudioManager am ;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                music.pause();
                music.seekTo(0);
            }

            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
                releaseMusic();

            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
                music.start();

        }
    };

    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMusic();
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE) ;

        final ArrayList<Word> words= new ArrayList<Word>() ;

        words.add(new Word("red", "weṭeṭṭi",R.drawable.color_red, R.raw.color_red)) ;
        words.add(new Word("green", "chokokki",R.drawable.color_green, R.raw.color_green)) ;
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown)) ;
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray)) ;
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black)) ;
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white)) ;
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow)) ;
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow)) ;

        ColorWordAdapter colorWordAdapter = new ColorWordAdapter(this, words) ;
        ListView listView = (ListView)(findViewById(R.id.list_view_color));
        listView.setAdapter(colorWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMusic();

                int res = am.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Word colorPos = words.get(i);

                    music = MediaPlayer.create(ColorsActivity.this, colorPos.getmMusicId());
                    Log.d(LOG_TAG, "Media Player created") ;

                    music.start();
                    Log.d(LOG_TAG, "Media Player started") ;

                    music.setOnCompletionListener(listener);

                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMusic();
    }

    private void releaseMusic() {   //if the object is finished playing the song we need to release it in order to release the memory
        //then set it to null in order to reinitialize it when a new song plays and is currently not playing any music
        if (music != null) {
            music.release();

            music = null;

            am.abandonAudioFocus(onAudioFocusChangeListener) ;
        }
    }
}
