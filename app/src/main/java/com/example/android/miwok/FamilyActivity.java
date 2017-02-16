package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

      private MediaPlayer mp ;
      private  AudioManager am ;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                        // Stop playback, because you lost the Audio Focus) {

                        mp.pause();
                        mp.seekTo(0);
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMusic();
                    }
                     else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mp.start();
                    }
                }
            };

     //We use OnCompletion because if we close our app then the whole app should be closed nothing should be used at that time
    // for eg. if music
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        //called when end of media playback is reached
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMusic();
        }
    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
         // important declaration for Audio Focus for getting started
         am = (AudioManager)getSystemService(Context.AUDIO_SERVICE) ;




        final ArrayList<Word> words= new ArrayList<Word>() ;

        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father)) ;
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother)) ;
        words.add(new Word("son", "angsi", R.drawable.family_daughter, R.raw.family_mother)) ;
        words.add(new Word("daughter", "tune", R.drawable.family_grandfather, R.raw.family_grandfather)) ;
        words.add(new Word("older brother", "taachi", R.drawable.family_grandmother, R.raw.family_grandmother)) ;
        words.add(new Word("younger brother", "chalitti", R.drawable.family_older_brother, R.raw.family_older_brother)) ;
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister)) ;
        words.add(new Word("younger sister", "kolliti", R.drawable.family_son, R.raw.family_son)) ;
        words.add(new Word("grandmother", "ama", R.drawable.family_younger_brother, R.raw.family_younger_brother)) ;
        words.add(new Word("grandfather", "paapa", R.drawable.family_younger_sister, R.raw.family_younger_sister)) ;

        FamilyWordAdapter familyWordAdapter = new FamilyWordAdapter(this, words) ;
        ListView listView = (ListView)(findViewById(R.id.list_view_family));
        listView.setAdapter(familyWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMusic();
                // request for playback
                int res = am.requestAudioFocus(afChangeListener,
                        //we want to request the audio focus on music
                        AudioManager.STREAM_MUSIC,
                        //we want the audio focus for short period of time
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) ;
                if (res==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //once we got the permission we should play the music
                    Word familyPos = words.get(i);

                    mp = MediaPlayer.create(FamilyActivity.this, familyPos.getmMusicId());
                    mp.start();

                    mp.setOnCompletionListener(listener);
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
        if (mp!=null) {
            mp.release();

            mp = null;
           // to unregister the Audio Focus
            am.abandonAudioFocus(afChangeListener) ;
        }
    }
}
