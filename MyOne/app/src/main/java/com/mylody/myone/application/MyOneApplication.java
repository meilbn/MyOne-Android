package com.mylody.myone.application;

import android.app.Application;

import com.mylody.myone.BuildConfig;

import timber.log.Timber;

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
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public MyOneApplication() {
        sApplication = this;
    }

    public static MyOneApplication getContext() {
        return sApplication;
    }

}
