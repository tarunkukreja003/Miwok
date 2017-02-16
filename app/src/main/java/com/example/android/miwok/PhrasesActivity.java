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

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mv;

    private AudioManager am ;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                mv.pause();
                mv.seekTo(0);
            }

            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
                releaseMusic();

            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
                mv.start();

        }
    };

    // we make this object global so that we won't create the object each time
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMusic();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE) ;

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        PhrasesWordAdapter wordPhraseAdapter = new PhrasesWordAdapter(this, words);
        ListView listView = (ListView) (findViewById(R.id.list_view_phrases));
        listView.setAdapter(wordPhraseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //release the music if it is currently exists
                releaseMusic();

                int res = am.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Word phrasePos = words.get(i);

                    mv = MediaPlayer.create(PhrasesActivity.this, phrasePos.getmMusicId());

                    mv.start();
                    // the following method is used to notify when the song is finished
                    mv.setOnCompletionListener(listener);


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
        if (mv!=null) {
            mv.release();

            mv = null;

            am.abandonAudioFocus(onAudioFocusChangeListener) ;
        }
    }

}
