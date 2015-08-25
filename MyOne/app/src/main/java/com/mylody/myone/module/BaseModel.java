package com.mylody.myone.module;

import com.android.volley.Request;
import com.mylody.myone.http.RequestManager;

/**
 * User:Shine
 * Date:2015-08-25
 * Description:
 */
public class BaseModel {

    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    public void cancelAll() {
        RequestManager.cancelAll(this);
    }
}
