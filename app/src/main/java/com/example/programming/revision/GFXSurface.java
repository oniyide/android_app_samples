package com.example.programming.revision;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by user on 12/30/2017.
 */

public class GFXSurface extends AppCompatActivity implements View.OnTouchListener{
    CustomSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY; // startingY, finalY animatedX etc
    Bitmap test, plus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new CustomSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0; y = 0; sX = 0; sY= 0; fX= 0; fY= 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX = motionEvent.getX();
                sY = motionEvent.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = motionEvent.getX();
                fY = motionEvent.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX/30;
                scaledY = dY/30;
                x = y = 0;
                break;
        }
        return true ;
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
                if (x != 0 && y != 0){
                    canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
                }
                if (sX != 0 && sY != 0){
                    canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);
                }
                if (fX != 0 && fY != 0){
                    canvas.drawBitmap(test, fX-(test.getWidth()/2)-aniX, fY-(test.getHeight()/2)-aniY, null);
                    canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);
                }
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;


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
















