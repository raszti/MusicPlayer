package com.example.android.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import static com.example.android.musicplayer.MainActivity.songs;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {


    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create an SongAdapter, whose data source is a list of Songs.
        // The adapter knows how to create list item views for each item
        // in the list.

        SongAdapter songAdapter = new SongAdapter(getActivity(), songs);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(songAdapter);

        return rootView;

    }

}
