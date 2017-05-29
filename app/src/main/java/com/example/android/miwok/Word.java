package com.example.android.miwok;

/**
 * Created by jesus on 10/05/17.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String  mMiwokTranslation;

    /** Miwok imageID */
    private int  mImageResourceID = NO_IMAGE_PROVIDED;

    //** Miwok sound ID */

    public int mSoundResourceID;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;


    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param SoundResourceID is for pronunciation of the word
     */
    public Word(String defaultTranslation, String miwokTranslation, int SoundResourceID) {

            mDefaultTranslation  = defaultTranslation;
            mMiwokTranslation  = miwokTranslation;
            mSoundResourceID = SoundResourceID;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param imageResourceID is the image for the word for numbers, colors and relatives.
     *
     * @param SoundResourceID is for pronunciation of the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int SoundResourceID) {

        mDefaultTranslation  = defaultTranslation;
        mMiwokTranslation  = miwokTranslation;
        mImageResourceID = imageResourceID;
        mSoundResourceID = SoundResourceID;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /*
    Get the ImageID resource of the word.
     */

    public int getImageResourseID(){

        return mImageResourceID;
    }

    public int getSoundResourseID(){

        return mSoundResourceID;
    }

    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    /**
     * Returns the string representation of the {@link Word} object.
     */

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mSoundResourceID=" + mSoundResourceID +
                '}';
    }



}
