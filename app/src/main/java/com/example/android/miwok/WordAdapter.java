package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jesus on 11/05/17.
 */


public class WordAdapter extends ArrayAdapter<Word> {

    int mcolorResource;

    // To create the Mediaplayer so we can play the pronunciation of the words.
    public MediaPlayer mp;

    /**
     * Handles audio focus when playing a sound file
     */

    private AudioManager mAudioManager;


    public WordAdapter(Activity context, ArrayList<Word> words, int colorResource) {


        super(context, 0, words);
        mcolorResource = colorResource;
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {


        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files+
                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mp.pause();
                mp.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mp.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Word currentword = (Word) getItem(position);

     /*
        To print out the values of the variables on the Log
      */

       // Log.v("NumbersActivity", "Current word: " + currentword);

        //Putting the texts on the listView.

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentword.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentword.getDefaultTranslation());

        // Set the images according to the texts of every activity
        ImageView imageResource = (ImageView) listItemView.findViewById(R.id.ImageResource);

        if (currentword.hasImage()) {
            imageResource.setImageResource(currentword.getImageResourseID());
            imageResource.setVisibility(View.VISIBLE);
        } else {
            imageResource.setVisibility(View.GONE);
        }

        //To change the background color of the ImageResource

        ImageView ImageResource = (ImageView) listItemView.findViewById(R.id.ImageResource);
        ImageResource.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.tan_background));


        //Find the listView by its ID.

        // To create the Mediaplayer so we can play the sound.

        // to play the sound when clicking on the textview

        View text = listItemView.findViewById(R.id.BackGroundColorTextViews);

        text.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create and setup the {@link AudioManager} to request audio focus
                // getContext to get the context, so the activity, the user is using at that moment???

                mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

                // it will Release the media player if it currently exists because we are about to play a different sound file

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        
                // Request audio focus so in order to play the audio file. The app needs to play a
               // short audio file, so we will request audio focus with a short amount of time
              // with AUDIOFOCUS_GAIN_TRANSIENT.

                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // We have audio focus now.
                    //Create the meadiaplayer
                    mp = MediaPlayer.create(getContext(), currentword.getSoundResourseID());

                    // Start the audio file
                    mp.start();
                    //Toast messsage with the miwok word
                   Toast.makeText(getContext(), currentword.getMiwokTranslation(), Toast.LENGTH_SHORT).show();
                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mp.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return listItemView;
    }

    public MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    /**
     * Clean up the media player by releasing its resources.
     */
    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}
