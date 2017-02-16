package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarunkukreja on 11/09/16.
 */
public class WordAdapter extends ArrayAdapter<Word>{


    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word currentWord = getItem(position) ;

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false) ;
        }

        TextView textView1 = (TextView)convertView.findViewById(R.id.default_text_view);
        textView1.setText(currentWord.getDeafaultTranslation());

        TextView textView2 = (TextView)convertView.findViewById(R.id.miwok_text_view);
        textView2.setText(currentWord.getMiwokTranslation());

        ImageView image= (ImageView)convertView.findViewById(R.id.image_number);
        image.setImageResource(currentWord.getImageId());



        return convertView ;
    }
}
