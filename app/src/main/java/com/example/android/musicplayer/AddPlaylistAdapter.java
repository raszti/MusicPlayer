package com.example.android.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddPlaylistAdapter extends ArrayAdapter<Playlist> {


    public AddPlaylistAdapter(Activity context, ArrayList<Playlist> playlist) {

        super(context, 0, playlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.addtoplaylist_list_item, parent, false);
        }

        // Get the Playlist object located at this position in the list
        final Playlist currentPlaylist = getItem(position);

        // Find the TextView in the addtoplaylist_text_view layout with the ID version_name
        TextView playlistTextView = (TextView) listItemView.findViewById(R.id.addtoplaylist_text_view);

        // Get the version name from the current Playlist object and
        // set this text on the playlistTextView
        playlistTextView.setText(currentPlaylist.getPlaylistName());


        //Find the addtoplaylist_button in the current View and set an OnClick method
        //Start a new activity and show Toast message with the name of the playlist the song was added
        ImageView addToPlaylist = (ImageView) listItemView.findViewById(R.id.addtopolaylist_button);
        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),MainActivity.class);
                Toast.makeText(getContext(), "Song added to " + currentPlaylist.getPlaylistName() ,Toast.LENGTH_SHORT).show();
                getContext().startActivity(i);
            }
        });

        // Return the whole list item layout (containing a TextView and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
