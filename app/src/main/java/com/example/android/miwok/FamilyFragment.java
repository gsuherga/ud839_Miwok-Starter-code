package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    //To use the WordAdapter and release the mediaplayer from here.

    WordAdapter adapter;

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /*
        To get the data list from the values (string)
         */

        String[] FamilyMiwok = getResources().getStringArray(R.array.FamilyMiwok);
        String[] FamilyDefault = getResources().getStringArray(R.array.FamilyDefault);
        int[] imageResourceID = {R.drawable.family_father, R.drawable.family_mother, R.drawable.family_son, R.drawable.family_daughter, R.drawable.family_older_brother, R.drawable.family_younger_brother, R.drawable.family_older_sister, R.drawable.family_younger_sister, R.drawable.family_grandmother, R.drawable.family_grandfather};
        int[] audioResourceID = {R.raw.family_father, R.raw.family_mother, R.raw.family_son, R.raw.family_daughter, R.raw.family_older_brother, R.raw.family_younger_brother, R.raw.family_older_sister, R.raw.family_younger_sister, R.raw.family_grandmother, R.raw.family_grandfather};

          /* for (int i = 0; i < numbers.length; i++) {
            Log.v("Numbers Activity", "com.example.android.miwok.Word at index" + i + " " + numbers[i]); //
        } */

        //  ArrayList<String> number = new ArrayList<String>(Arrays.asList(numbers));

        ArrayList<Word> words = new ArrayList<Word>();

        // Añadimos valores desde los valores para los Strings con un bucle for.

        for (int i = 0; i < FamilyDefault.length; i++) {

            words.add(new Word(FamilyDefault[i], FamilyMiwok[i], imageResourceID[i], audioResourceID[i]));
        }

        adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //To set the background color in the listview
        listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.category_family));

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
