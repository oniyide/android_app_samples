package com.example.programming.revision;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by user on 12/28/2017.
 */

public class OpenedClass extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotString, setData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getPrefs.getString("name", "Olugbo is...");  // remember the key name is "name" in prefs.xml | default value is "Olugbo is ..."
        String values = getPrefs.getString("list", "4");   // remember we set values 1, 2, 3, 4 in the array.xml

        if(values.contentEquals("1"))
            question.setText(et);

        //        Bundle gotBasket = getIntent().getExtras();
        //        gotString = gotBasket.getString("key");
        //        question.setText(gotString);
    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);

        returnData = (Button)findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);

        selectionList = (RadioGroup)findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle basket = new Bundle();
        Intent intent = new Intent();
        basket.putString("key", setData);
        intent.putExtras(basket);
        setResult(RESULT_OK, intent);// since we are starting this activity for a result --- the Data Activity
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.rImaculate:
                setData = "Probably right!";
                break;
            case R.id.rTheGreatest:
                setData = "Definitely right!";
                break;
            case R.id.rSuperHot:
                setData = "Spot On!";
                break;
        }
        test.setText(setData);
    }
}
