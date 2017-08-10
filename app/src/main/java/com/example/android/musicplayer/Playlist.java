package com.example.android.musicplayer;

import java.util.ArrayList;

public class Playlist {


    private String mPlaylistName;
    private ArrayList<Song> mPlaylist;


    public Playlist(String playlistName) {
        mPlaylistName = playlistName;
        mPlaylist = new ArrayList<Song>();
    }

    public void setPlaylistName(String playlistName){
        mPlaylistName = playlistName;
    }

    public String getPlaylistName()
    {
        return mPlaylistName;
    }

    public void addSong (Song song){
        mPlaylist.add(song);
    }

    public void removeSong (Song song, int id){
        mPlaylist.remove(id);
    }

    public ArrayList<Song> getSongList (){
        return mPlaylist;
    }
}
