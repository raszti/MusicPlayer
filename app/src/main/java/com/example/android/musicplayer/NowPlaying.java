package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.musicplayer.MainActivity.artistOnAir;
import static com.example.android.musicplayer.MainActivity.playStatus;
import static com.example.android.musicplayer.MainActivity.PLAY;
import static com.example.android.musicplayer.MainActivity.PAUSE;
import static com.example.android.musicplayer.MainActivity.songOnAir;


public class NowPlaying extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e) {
            System.out.println("Nullpointer exception error.");
        }

        // Set the content of the activity to use the activity_now_playingw_playing.xml layout file
        setContentView(R.layout.activity_now_playing);

        ImageView songs = (ImageView) findViewById(R.id.go_to_songlist);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SongLister.class);
                v.getContext().startActivity(i);
            }
        });

        TextView artistText = (TextView) findViewById(R.id.artist_now);
        TextView titleText = (TextView) findViewById(R.id.title_now);

        artistText.setText(artistOnAir);
        titleText.setText(songOnAir);

        final ImageView playNowButton = (ImageView) findViewById(R.id.play_now_button);
        if (playStatus == PLAY) {
            playNowButton.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
        } else {
            playNowButton.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
        }

        playNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (playStatus) {
                    case PLAY:
                        playStatus = PAUSE;
                        playNowButton.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
                        break;
                    default:
                        playStatus = PLAY;
                        playNowButton.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
                        break;
                }
            }
        });

        final ImageView img = (ImageView) findViewById(R.id.close_info_now_playing);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) findViewById(R.id.info_now_playing);
                txt.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_btn:
                Intent i = new Intent(NowPlaying.this, Shop.class);
                startActivity(i);
                Toast.makeText(NowPlaying.this, "MenuButtonClicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
