package com.example.android.musicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.musicplayer.MainActivity.playlists;
import static com.example.android.musicplayer.MainActivity.songs;

public class SongLister extends AppCompatActivity{

    private String listName;
    private ArrayList<Song> songArrayList = new ArrayList<Song>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the list.xml layout file
        setContentView(R.layout.list);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("listName") != null) {
                listName = bundle.getString("listName");
                playlistSelector(listName);
            } else if (bundle.getString("album") != null) {
                listName = bundle.getString("artist") + " - " + bundle.getString("album");
                albumSongSelector(bundle.getString("album"));
            } else if (bundle.getString("artist") != null) {
                listName = bundle.getString("artist");
                artistSongSelector(listName);
            } else {
                listName = getString(R.string.app_name);
            }
        } else {
            listName = getString(R.string.category_songs);
            songSelector();
        }

        setTitle(listName);

        if (listName.equals("PLAYLISTS")){
            // Create an AddPlaylistAdapter}, whose data source is a list of Playlists.
            // The adapter knows how to create list item views for each item
            // in the list.

            AddPlaylistAdapter addPlaylistAdapter = new AddPlaylistAdapter(this, playlists);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(addPlaylistAdapter);
        } else {
            // Create an SongAdapter, whose data source is a list of Songs.
            // The adapter knows how to create list item views for each item
            // in the list.

            SongAdapter songAdapter = new SongAdapter(this, songArrayList);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(songAdapter);
        }

    }

    private int getPlaylistID(String listName) {
        int playListID =0;

        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            if (p.getPlaylistName().equals(listName)) {
                playListID = i;
                break;
            }
        }
        return playListID;

    }

    private void playlistSelector(String playlist){

        for (int i = 0; i < playlists.get(getPlaylistID(listName)).getSongList().size(); i++) {
            songArrayList.add(playlists.get(getPlaylistID(listName)).getSongList().get(i));
        }

    }

    private void albumSongSelector(String album){

        for (int i = 0; i < songs.size(); i++){
            if (songs.get(i).getAlbum().equals(album)){
                songArrayList.add(songs.get(i));
            }
        }
    }

    private void artistSongSelector(String album){
        for (int i = 0; i < songs.size(); i++){
            if (songs.get(i).getNameOfArtist().equals(album)){
                songArrayList.add(songs.get(i));
            }
        }
    }

    private void songSelector(){
        for (int i = 0; i < songs.size(); i++){
            songArrayList.add(songs.get(i));
        }
    }

}
