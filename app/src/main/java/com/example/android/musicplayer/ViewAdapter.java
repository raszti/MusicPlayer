package com.example.android.musicplayer;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class ViewAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlaylistFragment();
        } else if (position == 1){
            return new SongsFragment();
        } else if (position == 2) {
            return new AlbumsFragment();
        }else {
            return new ArtistsFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_playlists);
        } else if (position == 1) {
            return mContext.getString(R.string.category_songs);
        } else if (position == 2) {
            return mContext.getString(R.string.category_albums);
        } else {
            return mContext.getString(R.string.category_artists);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}