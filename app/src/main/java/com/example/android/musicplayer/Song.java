package com.example.android.musicplayer;

public class Song {

    private String mNameOfArtist;
    private String mNameOfSong;
    private String mAlbum;
    private String mGenre;

    public Song(String nameOfArtist, String nameOfSong, String album, String genre)
    {
        mNameOfArtist = nameOfArtist;
        mNameOfSong = nameOfSong;
        mAlbum = album;
        mGenre = genre;
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
