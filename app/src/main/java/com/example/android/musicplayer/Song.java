package com.example.android.musicplayer;

public class Song {

    private String mNameOfArtist;
    private String mNameOfSong;
    private String mAlbum;

    public Song(String nameOfArtist, String nameOfSong, String album)
    {
        mNameOfArtist = nameOfArtist;
        mNameOfSong = nameOfSong;
        mAlbum = album;
    }

    public String getNameOfArtist()
    {
        return mNameOfArtist;
    }

    public String getNameOfSong()
    {
        return mNameOfSong;
    }

    public String getAlbum(){

        return mAlbum;
    }

}
