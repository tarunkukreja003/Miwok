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
 * Created by tarunkukreja on 12/09/16.
 */
public class FamilyWordAdapter extends ArrayAdapter<Word> {

    public FamilyWordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words); //we put 0 because we r not using ArrayAdapter
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word currentFamilyWord = getItem(position) ; //storing position in the object of Word class which will call its methods

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false) ;
        }
        // Adapter coonects with XML
        TextView defaultFamiyTextView = (TextView)convertView.findViewById(R.id.default_text_view_family);
        defaultFamiyTextView.setText(currentFamilyWord.getDeafaultTranslation());

        TextView miwokFamilyTextView = (TextView)convertView.findViewById(R.id.miwok_text_view_family);
        miwokFamilyTextView.setText(currentFamilyWord.getMiwokTranslation());

        ImageView imageView = (ImageView)convertView.findViewById(R.id.image1);
        imageView.setImageResource(currentFamilyWord.getImageId());

        return convertView ;
    }
}
