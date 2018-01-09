package com.example.programming.revision;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 1/1/2018.
 */

public class SimpleBrowser extends Activity implements View.OnClickListener {
    EditText url;
    WebView ourBrowser;
    Button go, back, refresh, forward, clearHistory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrowser = (WebView)findViewById(R.id.wvBrowser);
        ourBrowser.getSettings().setJavaScriptEnabled(true);   // enables JS
        ourBrowser.getSettings().setLoadWithOverviewMode(true);
        ourBrowser.getSettings().setUseWideViewPort(true);   // enables JS


        ourBrowser.setWebViewClient(new OurViewClient());

        try {
            ourBrowser.loadUrl("http://www.facebook.com");
        }catch (Exception e){
            e.printStackTrace();
        }

        go = (Button)findViewById(R.id.bGo);
        back = (Button)findViewById(R.id.bBack);
        refresh = (Button)findViewById(R.id.bRefresh);
        forward = (Button)findViewById(R.id.bForward);
        clearHistory = (Button)findViewById(R.id.bHistory);

        url = (EditText)findViewById(R.id.etURL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrowser.loadUrl(theWebsite);
                //hidingthe keyboard after using on EditText
                InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromInputMethod(url.getWindowToken(),0);
                break;
            case R.id.bBack:
                if (ourBrowser.canGoBack())
                    ourBrowser.goBack();
                break;
            case R.id.bForward:
                if (ourBrowser.canGoForward())
                    ourBrowser.goForward();
                break;
            case R.id.bRefresh:
                ourBrowser.reload();
                break;
            case R.id.bHistory:
                ourBrowser.clearHistory();
                break;

        }
    }
}

