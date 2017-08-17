package com.example.android.musicplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ShopAdapter extends ArrayAdapter<Song> {

    private ArrayList shoppingCart;

    public ShopAdapter(Activity context, ArrayList<Song> songs, ArrayList<Song> shoppingCart) {

        super(context, 0, songs);

        this.shoppingCart = shoppingCart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.shop_list_item, parent, false);
        }

        // Get the Song object located at this position in the list
        Song currentSong = getItem(position);

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

        ImageView imgAdd = (ImageView) listItemView.findViewById(R.id.shop_add_button);

        ImageView imgRemove = (ImageView) listItemView.findViewById(R.id.shop_remove_button);

        if (shoppingCart.contains(currentSong)){
            imgAdd.setVisibility(View.INVISIBLE);
            imgRemove.setVisibility(View.VISIBLE);
        } else if (!shoppingCart.contains(currentSong)) {
            imgAdd.setVisibility(View.VISIBLE);
            imgRemove.setVisibility(View.INVISIBLE);
        }

        // Return the whole list item layout (containing 2 TextViews and 2 ImageViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private void updateView(int index, ListView listView, Song currentSong) {
    }
}
