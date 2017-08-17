package com.example.android.musicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.example.android.musicplayer.MainActivity.playlists;
import static com.example.android.musicplayer.MainActivity.songs;

public class SongLister extends AppCompatActivity{

    private String listName;
    private ArrayList<Song> songArrayList = new ArrayList<Song>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the list.xml layout file
        setContentView(R.layout.activity_lister);


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
            ListView listView = (ListView) findViewById(R.id.lister);
            listView.setAdapter(addPlaylistAdapter);
        } else {
            // Create an SongAdapter, whose data source is a list of Songs.
            // The adapter knows how to create list item views for each item
            // in the list.

            SongAdapter songAdapter = new SongAdapter(this, songArrayList);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) findViewById(R.id.lister);
            listView.setAdapter(songAdapter);

            final ImageView img = (ImageView) findViewById(R.id.close_info_lister);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txt = (TextView) findViewById(R.id.info_lister);
                    txt.setVisibility(View.GONE);
                    img.setVisibility(View.GONE);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
