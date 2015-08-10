package com.mylody.myone.application;

import android.app.Application;

/**
 * User:Shine
 * Date:2015-08-10
 * Description:
 */
public class MyOneApplication extends Application {

    private static MyOneApplication sApplication;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MyOneApplication() {
        sApplication = this;
    }

    public static MyOneApplication getContext() {
        return sApplication;
    }

}
