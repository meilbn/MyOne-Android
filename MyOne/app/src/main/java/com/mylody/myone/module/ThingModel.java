package com.mylody.myone.module;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mylody.myone.bean.ThingBean;
import com.mylody.myone.http.ApiParams;
import com.mylody.myone.http.GsonRequest;
import com.mylody.myone.http.HttpConstant;
import com.mylody.myone.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * User: HappyHacking
 * Date: 2015-08-27
 * Time: 16:59
 * Description:
 */
public class ThingModel extends BaseModel {
    private final SimpleDateFormat mSimpleDateFormat;
    private final long mTimeMillis;

    public ThingModel(long timeMillis) {
        mTimeMillis = timeMillis;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void getOneThingEntity(int position, final ThingCallback callback) {
        long dateMillis = mTimeMillis - position * Constants.MILLIS_DAY;
        String strDate = mSimpleDateFormat.format(new Date(dateMillis));
        Timber.d("strDate:%s", strDate);

        ApiParams params = new ApiParams();
        params.setPath(HttpConstant.THING_GET_ONE_ENTITY);
        params.put("strDate", strDate);
        params.put("strRow", "1");
        String url = params.getUrl();
        Timber.d("请求东西接口:%s", url);
        GsonRequest request = new GsonRequest<>(url, ThingBean.class, new Response.Listener<ThingBean>() {
            @Override
            public void onResponse(ThingBean response) {
                callback.getOneThingEntitySuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.getOneThingEntityFailed();
            }
        });

        executeRequest(request);
    }

    public interface ThingCallback {
        void getOneThingEntitySuccess(ThingBean bean);

        void getOneThingEntityFailed();
    }
}
