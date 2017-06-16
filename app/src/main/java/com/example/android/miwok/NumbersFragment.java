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
 * Activities that contain this fragment must implement the
 *
 */
public class NumbersFragment extends Fragment {

    //To use the WordAdapter and release the mediaplayer from here.
    WordAdapter adapter;

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /*
        To get the data list from the values (string)
         */

        String [] numbersMiwok = getResources().getStringArray(R.array.NumbersMiwok);
        String [] numbersDefault = getResources().getStringArray(R.array.NumbersDefault);
        int [] imageResourceID = {R.drawable.number_one, R.drawable.number_two,R.drawable.number_three,R.drawable.number_four,R.drawable.number_five,R.drawable.number_six,R.drawable.number_seven,R.drawable.number_eight,R.drawable.number_nine,R.drawable.number_ten};
        int [] SoundResourceID = {R.raw.number_one,R.raw.number_two,R.raw.number_three,R.raw.number_four,R.raw.number_five,R.raw.number_six,R.raw.number_seven,R.raw.number_eight,R.raw.number_nine,R.raw.number_ten};


        //  ArrayList<String> number = new ArrayList<String>(Arrays.asList(numbers));

        ArrayList<Word> words = new ArrayList<Word>();

        // Añadimos valores desde los valores para los Strings con un bucle for.

        for (int i=0; i<numbersDefault.length; i++) {

            words.add(new Word(numbersDefault[i],numbersMiwok[i],imageResourceID[i],SoundResourceID[i]));
        }

        adapter = new WordAdapter (getActivity(),words,R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //To set the background color in the listview
        listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.category_numbers));

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
