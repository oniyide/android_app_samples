package com.example.programming.revision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 12/28/2017.
 */

public class Email extends AppCompatActivity implements View.OnClickListener {
    EditText personsEmail, intro, personsName, stupidThings, hatefulAction, outro;
    //    String emailAdd, beginning, name, stupidAction, hatefulAct, out;
    Button sendEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializeVars();

        sendEmail.setOnClickListener(this);
    }

    private void initializeVars() {
        personsEmail = (EditText)findViewById(R.id.etEmailAddress);
        intro = (EditText)findViewById(R.id.etIntro);
        personsName = (EditText)findViewById(R.id.etName);
        stupidThings = (EditText)findViewById(R.id.etThings);
        hatefulAction = (EditText)findViewById(R.id.etAction);
        outro = (EditText)findViewById(R.id.etOutro);

        sendEmail = (Button)findViewById(R.id.bSentEmail);

    }

    @Override
    public void onClick(View view) {
        String emailaddress[] = {personsEmail.getText().toString()};
        String message = "Well Hello, " +
                personsName.getText().toString() +
                "I just wanted to say " +
                intro.getText().toString() +
                ". Not only that but i hate when you " +
                stupidThings.getText().toString() +
                " , that just really makes me go loko. I just want to make " +
                hatefulAction.getText().toString() +
                ". \n That is all I needed to say. I am the best person you will ever meet. PS. I love you";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I hate You");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(emailIntent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
