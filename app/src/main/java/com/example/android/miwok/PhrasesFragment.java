package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    //To use the WordAdapter and release the mediaplayer from here.
    WordAdapter adapter;

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /*
        To get the data list from the values (string)
         */

        String[] PhrasesMiwok = getResources().getStringArray(R.array.PhrasesMiwok);
        String[] PhrasesDefault = getResources().getStringArray(R.array.PhrasesDefault);
        int[] SoundResourceID = {R.raw.phrase_where_are_you_going, R.raw.phrase_what_is_your_name, R.raw.phrase_my_name_is, R.raw.phrase_how_are_you_feeling, R.raw.phrase_im_feeling_good, R.raw.phrase_are_you_coming, R.raw.phrase_yes_im_coming, R.raw.phrase_im_coming, R.raw.phrase_lets_go, R.raw.phrase_come_here};

   /*     for (int i = 0; i < numbers.length; i++) {
            Log.v("Numbers Activity", "com.example.android.miwok.Word at index" + i + " " + numbers[i]); //
        } */

        //  ArrayList<String> number = new ArrayList<String>(Arrays.asList(numbers));

        ArrayList<Word> words = new ArrayList<Word>();

        // Añadimos valores desde los valores para los Strings con un bucle for.

        for (int i = 0; i < PhrasesDefault.length; i++) {

            words.add(new Word(PhrasesDefault[i], PhrasesMiwok[i], SoundResourceID[i]));
        }

        adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;

    }


    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        adapter.releaseMediaPlayer();
    }
}
