package com.example.programming.revision;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 12/27/2017.
 */

public class Splash extends AppCompatActivity {
    MediaPlayer song;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        song = MediaPlayer.create(this, R.raw.splashsound );
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("music", true);  // saying get the status of the music in preferences using the key "music" set default to true
        if (music == true)
             song.start();
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent openMainActivity = new Intent("com.example.programming.revision.MENU");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.release();
        finish();
    }
}
