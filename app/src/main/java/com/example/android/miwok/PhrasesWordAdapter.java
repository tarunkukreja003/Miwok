package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 11/09/16.
 */
public class PhrasesWordAdapter extends ArrayAdapter<Word> {

    public PhrasesWordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word currentPhraseWord = getItem(position) ;

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_phrases, parent, false) ;
        }

        TextView defaultPhrasetextView = (TextView)convertView.findViewById(R.id.default_text_view_phrase);
        defaultPhrasetextView.setText(currentPhraseWord.getDeafaultTranslation());

        TextView miwokPhraseTextView = (TextView)convertView.findViewById(R.id.miwok_text_view_phrase);
        miwokPhraseTextView.setText(currentPhraseWord.getMiwokTranslation());

        return convertView ;
    }
}
