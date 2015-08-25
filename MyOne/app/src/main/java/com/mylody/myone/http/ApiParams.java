package com.mylody.myone.http;

import java.util.concurrent.ConcurrentHashMap;

/**
 * User:Shine
 * Date:2015-08-11
 * Description:
 */
public class ApiParams {

    protected final ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    private String mPath;

    public void setPath(String path) {
        this.mPath = path;
    }

    /**
     * Adds a int value to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value int for the new param.
     */
    public void put(String key, Object value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    public String getUrl() {
        StringBuilder result = new StringBuilder(HttpConstant.URL);
        result.append(mPath).append("?");
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
            if (result.length() > 0)
                result.append("&");
        }

        return result.toString().substring(0, result.length() - 1);
    }

    public String getUrl(String... values) {
        StringBuilder result = new StringBuilder(HttpConstant.URL);
        for (String value : values) {
            result.append(value).append("/");
        }
        return result.toString();
    }
}
