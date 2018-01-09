package com.example.programming.revision;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 12/28/2017.
 */

public class Camera extends AppCompatActivity implements View.OnClickListener{
    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int CameraData = 0;
    Bitmap bmp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making te activity Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //

        setContentView(R.layout.photo);
        initialize();
        InputStream is = getResources().openRawResource(+ R.mipmap.ic_launcher);
        bmp = BitmapFactory.decodeStream(is);

    }

    private void initialize() {
        ib = (ImageButton)findViewById(R.id.ibTakePic);
        b = (Button)findViewById(R.id.bSetWall);
        iv = (ImageView)findViewById(R.id.ivReturnedPic);
        ib.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibTakePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CameraData);
                break;
            case R.id.bSetWall:
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try{
                    wallpaperManager.setBitmap(bmp);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
