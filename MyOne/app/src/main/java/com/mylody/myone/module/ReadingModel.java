package com.mylody.myone.module;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.http.ApiParams;
import com.mylody.myone.http.GsonRequest;
import com.mylody.myone.http.HttpConstant;
import com.mylody.myone.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * User:Shine
 * Date:2015-08-25
 * Description:
 */
public class ReadingModel extends BaseModel {

    private final SimpleDateFormat mSimpleDateFormat;
    private final long mTimeMillis;

    public ReadingModel(long timeMillis) {
        mTimeMillis = timeMillis;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void getOneContentInfo(int position, final ReadingCallback callback) {
        long dateMillis = mTimeMillis - position * Constants.MILLIS_DAY;
        String strDate = mSimpleDateFormat.format(new Date(dateMillis));
        Timber.d("strDate:%s", strDate);

        ApiParams params = new ApiParams();
        params.setPath(HttpConstant.READING_GET_ONE_CONTENT_INFO);
        params.put("strDate", strDate);
        params.put("strLastUpdateDate", strDate);
        String url = params.getUrl();
        Timber.d("请求文章接口:%s", url);
        GsonRequest request = new GsonRequest<>(url, ReadingBean.class, new Response.Listener<ReadingBean>() {
            @Override
            public void onResponse(ReadingBean response) {
                callback.getOneContentInfoSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.getOneContentInfoError();
            }
        });

        executeRequest(request);
    }

    public interface ReadingCallback {
        void getOneContentInfoSuccess(ReadingBean bean);

        void getOneContentInfoError();
    }

}
