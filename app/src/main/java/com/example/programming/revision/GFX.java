package com.example.programming.revision;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 12/30/2017.
 */

public class GFX extends AppCompatActivity {
    PowerManager pm;
    PowerManager.WakeLock wl;
    CustomView ourView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //wake lock ----------- to dim screen and save battrey power
        pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "whatever");

        super.onCreate(savedInstanceState);
        wl.acquire();// starting the wakelock
        ourView = new CustomView(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }

}
