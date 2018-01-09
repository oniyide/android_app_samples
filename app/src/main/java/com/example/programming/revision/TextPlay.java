package com.example.programming.revision;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by user on 12/27/2017.
 */

public class TextPlay extends AppCompatActivity implements View.OnClickListener {
    Button chkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        setcomponents();
        /*
        passTog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passTog.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        */
        passTog.setOnClickListener(this);
        chkCmd.setOnClickListener(this);

    }

    private void setcomponents() {
        chkCmd = (Button)findViewById(R.id.bResult);
        passTog = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommand);
        display = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bResult:
                String check = input.getText().toString();
                if (check.contentEquals("left") ){
                    display.setGravity(Gravity.LEFT);
                }

                else if (check.contentEquals("center") ){
                    display.setGravity(Gravity.CENTER);
                }

                else if (check.contentEquals("right") ){
                    display.setGravity(Gravity.RIGHT);
                }
                else if (check.contentEquals("blue") ){
                    display.setTextColor(Color.BLUE);
                }
                else if (check.contains("WTF") ){
                    Random random = new Random();
                    display.setText("WTF!!!");
                    display.setTextSize(random.nextInt(75));
                    display.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

                    switch (random.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.RIGHT);
                            break;
                        case 2:
                            display.setGravity(Gravity.CENTER);
                            break;
                    }
                }
                else {
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.BLACK);
                }

                break;
            case R.id.tbPassword:
                if (passTog.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
