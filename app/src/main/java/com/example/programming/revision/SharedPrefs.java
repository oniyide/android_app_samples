package com.example.programming.revision;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends AppCompatActivity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResults;
    Button bSave, bLoad;
    SharedPreferences someData;

    static String filename = "MySharedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);

        sharedData = (EditText)findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

        bSave = (Button) findViewById(R.id.bSave);
        bLoad = (Button) findViewById(R.id.bLoad);

        bSave.setOnClickListener(this);
        bLoad.setOnClickListener(this);

        someData = getSharedPreferences(filename, 0);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSave:
                String stringData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString", stringData);
                editor.commit();
                break;
            case R.id.bLoad:
                someData = getSharedPreferences(filename, 0);
                String dataReturned = someData.getString("sharedString", "Couldn't Load Data");
                dataResults.setText(dataReturned);
                break;
        }
    }
}
