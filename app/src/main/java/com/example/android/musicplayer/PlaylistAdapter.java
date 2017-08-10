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

import static com.example.android.musicplayer.MainActivity.PLAY;
import static com.example.android.musicplayer.MainActivity.artistOnAir;
import static com.example.android.musicplayer.MainActivity.playStatus;
import static com.example.android.musicplayer.MainActivity.songOnAir;


public class PlaylistAdapter extends ArrayAdapter<Playlist> {


    public PlaylistAdapter(Activity context, ArrayList<Playlist> playlist) {

        super(context, 0, playlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist_list_item, parent, false);
        }

        // Get the Playlist object located at this position in the list
        final Playlist currentPlaylist = getItem(position);

        // Find the TextView in the playlist_text_view layout with the ID version_name
        TextView playlistTextView = (TextView) listItemView.findViewById(R.id.playlist_text_view);

        // Get the version name from the current Song object and
        // set this text on the playlistTextView
        playlistTextView.setText(currentPlaylist.getPlaylistName());

        ImageView playlistPlay = (ImageView) listItemView.findViewById(R.id.playlist_play_button);
        playlistPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                songOnAir = currentPlaylist.getSongList().get(0).getNameOfSong();
                artistOnAir = currentPlaylist.getSongList().get(0).getNameOfArtist();
                playStatus = PLAY;

                Intent i = new Intent(getContext(),NowPlaying.class);
                getContext().startActivity(i);
            }
        });

        ImageView playlistSongs = (ImageView) listItemView.findViewById(R.id.list_songs_button);
        playlistSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),SongLister.class);
                i.putExtra("listName",currentPlaylist.getPlaylistName());
                getContext().startActivity(i);
            }
        });

        // Return the whole list item layout (containing a TextView and 2 ImageViews )
        // so that it can be shown in the ListView
        return listItemView;
    }
}
