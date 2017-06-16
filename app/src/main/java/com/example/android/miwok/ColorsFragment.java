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
public class ColorsFragment extends Fragment {


    public ColorsFragment() {
        // Required empty public constructor
    }
    //To use the WordAdapter and release the mediaplayer from here.
    WordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

     /*
        To get the data list from the values (string)
         */

        String[] ColorsMiwok = getResources().getStringArray(R.array.ColorMiwok);
        String[] ColorsDefault = getResources().getStringArray(R.array.ColorDefault);
        int[] imageResourceID = {R.drawable.color_red, R.drawable.color_mustard_yellow, R.drawable.color_dusty_yellow, R.drawable.color_green, R.drawable.color_brown, R.drawable.color_gray, R.drawable.color_black, R.drawable.color_white};
        int[] SoundResourceID = {R.raw.color_red, R.raw.color_mustard_yellow, R.raw.color_dusty_yellow, R.raw.color_green, R.raw.color_brown, R.raw.color_gray, R.raw.color_black, R.raw.color_white};

   /*     for (int i = 0; i < numbers.length; i++) {
            Log.v("Numbers Activity", "com.example.android.miwok.Word at index" + i + " " + numbers[i]); //
        } */

        //  ArrayList<String> number = new ArrayList<String>(Arrays.asList(numbers));

        ArrayList<Word> words = new ArrayList<Word>();

        // Añadimos valores desde los valores para los Strings con un bucle for.

        for (int i = 0; i < ColorsDefault.length; i++) {

            words.add(new Word(ColorsDefault[i], ColorsMiwok[i], imageResourceID[i], SoundResourceID[i]));
        }

        adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //To set the background color in the listview
        listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.category_colors));

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
