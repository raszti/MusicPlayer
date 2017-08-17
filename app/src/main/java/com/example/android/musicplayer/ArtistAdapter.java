package com.example.android.musicplayer;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.TextView;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class ArtistAdapter extends ArrayAdapter<Song> {


    public ArtistAdapter(Activity context, ArrayList<Song> songs) {

        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.artist_list_item, parent, false);
        }

        // Get the Song object located at this position in the list
        final Song currentSong = getItem(position);

        // Find the TextView in the artist_text_view layout with the ID version_name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_text_view);

        // Get the version name from the current Song object and
        // set this text on the artistTextView
        artistTextView.setText(currentSong.getNameOfArtist());

        ImageView songOfArtist = (ImageView) listItemView.findViewById(R.id.artist_songs_button);
        songOfArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),SongLister.class);
                i.putExtra("artist", currentSong.getNameOfArtist());
                getContext().startActivity(i);
            }
        });


        // Return the whole list item layout (containing a TextView and an ImageView )
        // so that it can be shown in the ListView
        return listItemView;
    }
}
