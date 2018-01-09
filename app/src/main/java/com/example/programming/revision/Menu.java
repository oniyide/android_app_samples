package com.example.programming.revision;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 12/27/2017.
 */
// getting the current time = Sysytem.currentTimeMillis() --------type long
public class Menu extends ListActivity {
    String classes[] = {"MainActivity", "TextPlay", "Email", "Camera", "Data", "GFX", "GFXSurface", "SoundStuff",
    "NavDrawer", "Tabs", "SimpleBrowser", "Flipper","SharedPrefs", "InternalData", "ExternalData", "SQLiteExample", "Accelerate", "Maps"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String classpicked = classes[position];
        try {
            Class ourClass = Class.forName("com.example.programming.revision." + classpicked);
            Intent ourIntent = new Intent(this, ourClass);
            startActivity(ourIntent);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    //        return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.aboutUs:
                Intent iA = new Intent("com.example.programming.revision.ABOUT");
                startActivity(iA);
                break;
            case R.id.preferences:
                Intent iP = new Intent("com.example.programming.revision.PREFS");
                startActivity(iP);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }
}
