package com.example.android.miwok;

/**
 * Created by tarunkukreja on 11/09/16.
 */
public class Word {

    private String mMiwokTranslation ;

    private String mDefaultTranslation ;

    private int mImage ;

    private int mMusic ;

    public Word(String defaultTranslation, String miwokTranslation, int imageId, int musicId){

        mDefaultTranslation=defaultTranslation ;
        mMiwokTranslation=miwokTranslation ;
        mImage=imageId ;
        mMusic=musicId ;
    }

    public Word(String defaultTranslation, String miwokTranslation, int musicId ) {
        mDefaultTranslation=defaultTranslation ;
        mMiwokTranslation=miwokTranslation ;
        mMusic=musicId ;
    }

    public String getMiwokTranslation(){

        return mMiwokTranslation ;
    }

    public String getDeafaultTranslation(){

        return mDefaultTranslation ;
    }

    public int getImageId()
    {
        return mImage ;
    }

    public int getmMusicId() {
        return mMusic ;
    }
}
