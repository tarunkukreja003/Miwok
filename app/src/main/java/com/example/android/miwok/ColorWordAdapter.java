package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tarunkukreja on 11/09/16.
 */
public class ColorWordAdapter extends ArrayAdapter<Word> {

    public ColorWordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Word currentColorWord = getItem(position) ;

            if(convertView==null)
            {
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_color, parent, false) ;
            }

            TextView defaultColorTextView = (TextView)convertView.findViewById(R.id.default_text_view_color);
            defaultColorTextView.setText(currentColorWord.getDeafaultTranslation());

            TextView miwokColortextView = (TextView)convertView.findViewById(R.id.miwok_text_view_color);
            miwokColortextView.setText(currentColorWord.getMiwokTranslation());

            ImageView image= (ImageView)convertView.findViewById(R.id.image);
            image.setImageResource(currentColorWord.getImageId());


            return convertView ;
        }
    }

