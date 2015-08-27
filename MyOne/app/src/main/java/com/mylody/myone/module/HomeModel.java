package com.mylody.myone.module;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mylody.myone.bean.HomeBean;
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
 * Time: 14:37
 * Description:
 */
public class HomeModel extends BaseModel {
    private final SimpleDateFormat mSimpleDateFormat;
    private final long mTimeMillis;

    public HomeModel(long timeMillis) {
        mTimeMillis = timeMillis;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void getOneHomeEntity(int position, final HomeCallback callback) {
        long dateMillis = mTimeMillis - position * Constants.MILLIS_DAY;
        String strDate = mSimpleDateFormat.format(new Date(dateMillis));
        Timber.d("strDate:%s", strDate);

        ApiParams params = new ApiParams();
        params.setPath(HttpConstant.HOME_GET_ONE_ENTITY);
        params.put("strDate", strDate);
        params.put("strRow", "1");
        String url = params.getUrl();
        Timber.d("请求首页接口:%s", url);
        GsonRequest request = new GsonRequest<>(url, HomeBean.class, new Response.Listener<HomeBean>() {
            @Override
            public void onResponse(HomeBean response) {
                callback.getOneHomeEntitySuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.getOneHomeEntityFailed();
            }
        });

        executeRequest(request);
    }

    public interface HomeCallback {
        void getOneHomeEntitySuccess(HomeBean bean);

        void getOneHomeEntityFailed();
    }

}
