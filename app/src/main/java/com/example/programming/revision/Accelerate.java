package com.example.programming.revision;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by user on 1/6/2018.
 */

public class Accelerate extends Activity implements SensorEventListener {
    float x, y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;
    CustomSurface customsurface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0);
        {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        x = y = sensorY = sensorX = 0;
        customsurface = new CustomSurface(this);
        customsurface.resume();
        setContentView(customsurface);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = sensorEvent.values[0];
        sensorY = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        customsurface.pause();
    }

    class CustomSurface extends SurfaceView implements Runnable {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        Boolean isRunning = false;
        public CustomSurface(Context context) {
            super(context);
            ourHolder = getHolder();

        }

        @Override
        public void run() {
            while (isRunning){
                if (!ourHolder.getSurface().isValid())
                    continue; // do not execute the remaining code in the while loop and start loop again till condition is true
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2,2,150);
                float centerX = canvas.getWidth()/2;
                float centerY = canvas.getHeight()/2;
                canvas.drawBitmap(ball, centerX + sensorX*20, centerY + sensorY*20, null);


                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            isRunning = false;
            while(true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }
    }
}
