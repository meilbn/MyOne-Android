package com.mylody.myone.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.mylody.myone.R;

public class AboutActivity extends BaseActivity {

    WebView mWebView;
    TextView mTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mWebView = (WebView) findViewById(R.id.aboutWebView);
        mWebView.loadUrl("http://m.wufazhuce.com/about?from=ONEApp");

        mTitleTV = (TextView) findViewById(R.id.title_bar_title_textview);
        mTitleTV.setText("关于");
    }

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    public void onClickReturn() {
        finish();
    }

}
