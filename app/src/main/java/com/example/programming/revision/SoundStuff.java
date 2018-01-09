package com.example.programming.revision;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 12/30/2017.
 */
@SuppressWarnings("deprecation")
public class SoundStuff extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    SoundPool sp; // for short sounds like .ogg
    int explosion = 0;
    MediaPlayer mp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {

            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
        explosion =sp.load(this, R.raw.explosion, 1);
        mp = MediaPlayer.create(this, R.raw.backgroundmusic);

    }

    @Override
    public void onClick(View view) {
        if(explosion != 0)
            sp.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View view) {
        mp.start();
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
        finish();
    }
}
