package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Song> songs;
    public static ArrayList<Playlist> playlists;
    public static Playlist playlist1;
    public static Playlist playlist2;
    public static String songOnAir;
    public static String artistOnAir;
    public static int playStatus;
    public static final int PLAY = 1;
    public static final int PAUSE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //create an array of songs for the presentation of the app.
        songs = new ArrayList<Song>();

        songs.add(new Song("Kanizsa Gina","Fall like rain","Tears","Jazz"));
        songs.add(new Song("Chase","Dust in the wind","7th of July","Pop"));
        songs.add(new Song("Jamiroquai","White knuckle ride","Rock Dust Light Star","Funky"));
        songs.add(new Song("Snoop Dogg","Sensual seduction","Ego Trippin'","Hip hop"));
        songs.add(new Song("Metric","Sick Muse","Fantasies","Indie"));
        songs.add(new Song("Slipknot","Duality","The Subliminal Verses","Metal"));
        songs.add(new Song("Mius","Broken words","Eigengrau","Electronic"));
        songs.add(new Song("Szalóki Ági","Érik a cseresznye","Gingalló","Folk"));

        playlists = new ArrayList<Playlist>();

        playlist1 = new Playlist("Party songs");
        playlists.add(playlist1);

        playlist2 = new Playlist("Favourites");
        playlists.add(playlist2);

        playlist1.addSong(songs.get(0));
        playlist1.addSong(songs.get(1));
        playlist1.addSong(songs.get(2));
        playlist1.addSong(songs.get(3));
        System.out.println(playlist1.getSongList().size());

        playlist2.addSong(songs.get(4));
        playlist2.addSong(songs.get(5));
        playlist2.addSong(songs.get(6));
        playlist2.addSong(songs.get(7));
        System.out.println(playlist1.getSongList().size());


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        ViewAdapter adapter = new ViewAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        if ((songOnAir == null) && (artistOnAir == null)) {
            songOnAir = songs.get(0).getNameOfSong();
            artistOnAir = songs.get(0).getNameOfArtist();
        }

        final TextView songOnAirText = (TextView) findViewById(R.id.song_on_air_text_view);
        songOnAirText.setText(songOnAir);
        TextView artistOnAirText = (TextView) findViewById(R.id.artist_on_air_text_view);
        artistOnAirText.setText(artistOnAir);

        final ImageView playOnAir = (ImageView) findViewById(R.id.play_on_air_button);
        if (playStatus == PLAY) {
            playOnAir.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
        } else {
            playOnAir.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
        }

        View onAir = (View) findViewById(R.id.on_air);
        onAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NowPlaying.class);
                i.putExtra("artist", artistOnAir);
                i.putExtra("title", songOnAir);
                v.getContext().startActivity(i);
            }
        });

        playOnAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (playStatus) {
                    case PLAY:
                        playStatus = PAUSE;
                        playOnAir.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
                        break;
                    default:
                        playStatus = PLAY;
                        playOnAir.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
                        break;
                }
            }

        });
    };
}
