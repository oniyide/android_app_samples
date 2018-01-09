package com.example.programming.revision;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 1/1/2018.
 */

public class InternalData extends AppCompatActivity implements View.OnClickListener{
    EditText sharedData;
    TextView dataResults;
    Button bSave, bLoad;
    FileOutputStream fos;
    String FILENAME = "InternalString";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);

        sharedData = (EditText)findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

        bSave = (Button) findViewById(R.id.bSave);
        bLoad = (Button) findViewById(R.id.bLoad);

        bSave.setOnClickListener(this);
        bLoad.setOnClickListener(this);

        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSave:
                String data = sharedData.getText().toString();
                /*
                File f = new File(FILENAME);
                try {
                    fos = new FileOutputStream(f);
                    // write some data
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bLoad:
                new LoadSomeStuff().execute(FILENAME);  // where filename is the first parameter in the AsyncTask class AsyncTask<String, Integer, String>
                break;
        }
    }

    public class LoadSomeStuff extends AsyncTask<String, Integer, String>{

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            // do something like display a progressbar
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) { // where strings is the first parameter in AsyncTask<String, Integer, String>
            String collected = null;
            for (int i=0; i<= 20; i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();

            try {
                FileInputStream fis = openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1){
                    collected = new String(dataArray);
                }
                fis.close();
                return(collected);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //where values is the second parameter in AsyncTask<String, Integer, String>
//            super.onProgressUpdate(values);
            // do something like update progressbar

            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {  //where s in the thirs parameter in AsyncTask<String, Integer, String>
//            super.onPostExecute(s);
//            do something like hide the progress bar or change a textview
            dataResults.setText(s);
        }



    }
}
