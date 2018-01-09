package com.example.programming.revision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 12/28/2017.
 */

public class Data extends AppCompatActivity implements View.OnClickListener {
    EditText sendET;
    Button start, startFor;
    TextView gotAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    private void initialize() {
        sendET = (EditText)findViewById(R.id.etSend);
        start = (Button)findViewById(R.id.bSA);
        startFor = (Button)findViewById(R.id.bSAFR);
        gotAnswer = (TextView)findViewById(R.id.tvGot);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSA:
                String string = sendET.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("key", string);
                Intent i = new Intent(this, OpenedClass.class);
                i.putExtras(basket);
                startActivity(i);
                break;
            case R.id.bSAFR:
                Intent intent = new Intent(this, OpenedClass.class);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle basket = data.getExtras();
            String s = basket.getString("key");
            gotAnswer.setText(s);
        }
    }

}
