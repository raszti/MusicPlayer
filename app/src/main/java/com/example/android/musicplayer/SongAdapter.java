package com.example.android.musicplayer;

import android.content.Intent;
import android.widget.TextView;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.example.android.musicplayer.MainActivity.PLAY;
import static com.example.android.musicplayer.MainActivity.artistOnAir;
import static com.example.android.musicplayer.MainActivity.playStatus;
import static com.example.android.musicplayer.MainActivity.songOnAir;


public class SongAdapter extends ArrayAdapter<Song> {


    public SongAdapter(Activity context, ArrayList<Song> songs) {

        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final Song currentSong = getItem(position);

        // Find the TextView in the song_list_item.xml layout with the ID version_name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_text_view);
        // Get the name of the artist from the current Song object and
        // set this text on the name TextView
        artistTextView.setText(currentSong.getNameOfArtist());

        // Find the TextView in the songlist_item.xml layout with the ID version_number
        TextView songTextView = (TextView) listItemView.findViewById(R.id.song_text_view);
        // Get the name of the song from the current Song object and
        // set this text on the songTextView
        songTextView.setText(currentSong.getNameOfSong());

        ImageView playButton = (ImageView) listItemView.findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                songOnAir = currentSong.getNameOfSong();
                artistOnAir = currentSong.getNameOfArtist();

                playStatus = PLAY;

                Intent i = new Intent(getContext(),NowPlaying.class);
                getContext().startActivity(i);
            }
        });

        final ImageView addToPlaylist = (ImageView) listItemView.findViewById(R.id.addtolist_button);
        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SongLister.class);
                i.putExtra("listName", "PLAYLISTS");
                getContext().startActivity(i);
            }
        });


        // Return the whole list item layout (containing 2 TextViews and 2 ImageViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
