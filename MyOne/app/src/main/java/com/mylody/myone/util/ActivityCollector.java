package com.mylody.myone.util;

import android.support.v4.app.FragmentActivity;

import com.mylody.myone.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: meilebin
 * Date: 2015-08-12
 * Time: 11:20
 * Description:
 */
public class ActivityCollector {
    private static List<BaseActivity> activities = new ArrayList<BaseActivity>();

    public static void addActivity(BaseActivity fragmentActivity) {
        activities.add(fragmentActivity);
    }

    public static void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (FragmentActivity fragmentActivity : activities) {
            if (!fragmentActivity.isFinishing()) {
                fragmentActivity.finish();
            }
        }
    }

    public static void exit(){
        finishAll();
        System.exit(0);
    }
}
