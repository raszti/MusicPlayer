package com.example.android.musicplayer;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AlbumAdapter extends ArrayAdapter<Song> {


    public AlbumAdapter(Activity context, ArrayList<Song> songs) {

        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.album_list_item, parent, false);
        }

        // Get the Song object located at this position in the list
        final Song currentSong = getItem(position);

        // Find the TextView in the album_text_view layout with the ID version_name
        TextView albumTextView = (TextView) listItemView.findViewById(R.id.album_text_view);

        // Get the version name from the current Song object and
        // set this text on the name albumTextView
        albumTextView.setText(currentSong.getAlbum());

        // Find the TextView in the artist_text_view layout with the ID version_name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_text_view);

        // Get the version name from the current Song object and
        // set this text on the name artistTextView
        artistTextView.setText(currentSong.getNameOfArtist());

        // Find the albumSongs image with the ID version name
        // set up an OnClickListener
        // On click start SongLister activity and send the name of the album and the name of the artist with the intent.
        ImageView albumSongs = (ImageView) listItemView.findViewById(R.id.album_songs_button);
        albumSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),SongLister.class);
                i.putExtra("album", currentSong.getAlbum());
                i.putExtra("artist", currentSong.getNameOfArtist());
                getContext().startActivity(i);
            }
        });


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
