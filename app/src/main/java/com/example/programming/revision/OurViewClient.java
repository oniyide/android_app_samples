package com.example.programming.revision;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by user on 1/1/2018.
 */

class OurViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return  true;
    }
}
