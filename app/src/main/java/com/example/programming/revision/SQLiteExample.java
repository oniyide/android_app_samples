package com.example.programming.revision;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 1/3/2018.
 */

public class SQLiteExample extends AppCompatActivity implements View.OnClickListener {

    EditText sqlRow;
    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlName = (EditText)findViewById(R.id.etSQLName);
        sqlHotness = (EditText)findViewById(R.id.etSQLHotness);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);

        sqlRow = (EditText)findViewById(R.id.etSQLrowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);

        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                }
                catch (Exception e){
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                finally {
                    if(didItWork){
                        Dialog d = new Dialog(this);
                        d.setTitle("It worked");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.bSQLopenView:
                Intent i = new Intent("com.example.programming.revision.SQLVIEW");
                startActivity(i);
                break;
            case R.id.bSQLmodify:
                try {
                    String sRow = sqlRow.getText().toString();
                    String mName = sqlName.getText().toString();
                    String mHotness = sqlHotness.getText().toString();

                    long lRow = Long.parseLong(sRow);
                    HotOrNot ex = new HotOrNot(SQLiteExample.this);
                    ex.open();
                    ex.updateEntry(lRow, mName, mHotness);
                    ex.close();
                    Toast t = Toast.makeText(SQLiteExample.this, "Field Modified", Toast.LENGTH_SHORT);
                    t.show();
                }
                catch (Exception e){
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.bgetInfo:
                try {
                    String s = sqlRow.getText().toString();
                    long l = Long.parseLong(s);
                    HotOrNot hon = new HotOrNot(SQLiteExample.this);
                    hon.open();
                    String returnedName = hon.getName(l);
                    String returnedHotness = hon.getHotness(l);
                    hon.close();
                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);
                    Toast t = Toast.makeText(SQLiteExample.this, "Info Retrieved", Toast.LENGTH_SHORT);
                    t.show();
                }catch (Exception e){
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.bSQLdelete:
                try {
                    String sRow1 = sqlRow.getText().toString();
                    long lRow1 = Long.parseLong(sRow1);
                    HotOrNot ex1 = new HotOrNot(SQLiteExample.this);
                    ex1.open();
                    ex1.deleteEntry(lRow1);
                    ex1.close();
                    Toast t = Toast.makeText(SQLiteExample.this, "Field Deleted", Toast.LENGTH_SHORT);
                    t.show();
                }
                catch (Exception e){
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }

    }
}
