package com.mylody.myone.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.mylody.myone.R;

import butterknife.InjectView;

public class AboutActivity extends BaseActivity {

    @InjectView(R.id.about_webbiew)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mWebView.loadUrl("http://m.wufazhuce.com/about?from=ONEApp");
    }

}
