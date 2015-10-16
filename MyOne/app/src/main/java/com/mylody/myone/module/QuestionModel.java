package com.mylody.myone.module;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mylody.myone.bean.QuestionBean;
import com.mylody.myone.http.ApiParams;
import com.mylody.myone.http.GsonRequest;
import com.mylody.myone.http.HttpConstant;
import com.mylody.myone.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * User:Shine
 * Date:2015-10-16
 * Description:
 */
public class QuestionModel extends BaseModel {

    private final SimpleDateFormat mSimpleDateFormat;
    private long mTimeMillis;

    public QuestionModel(long timeMillis) {
        mTimeMillis = timeMillis;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void getQuestionInfo(final int position, final QuestionCallback callback) {
        long dateMillis = mTimeMillis - position * Constants.MILLIS_DAY;
        String strDate = mSimpleDateFormat.format(new Date(dateMillis));
        Timber.d("strDate:%s", strDate);

        ApiParams params = new ApiParams();
        params.setPath(HttpConstant.QUESTION_GET_ONE_ENTITY);
        params.put("strDate", strDate);
        params.put("strLastUpdateDate", strDate);
        String url = params.getUrl();
        Timber.d("请求文章接口:%s", url);
        GsonRequest request = new GsonRequest<>(url, QuestionBean.class, new Response.Listener<QuestionBean>() {
            @Override
            public void onResponse(QuestionBean response) {
                callback.getQuestionInfoSuccess(position, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.getQuestionInfoError(position);
            }
        });

        executeRequest(request);
    }

    public long getTimeMillis() {
        return mTimeMillis;
    }

    public String getDateByTimeMillis(long timeMillis) {
        String strDate = mSimpleDateFormat.format(new Date(timeMillis));
        return strDate;
    }

    public void setTimeMillis(long timeMillis) {
        mTimeMillis = timeMillis;
    }

    public interface QuestionCallback {
        void getQuestionInfoSuccess(int position, QuestionBean bean);

        void getQuestionInfoError(int position);
    }


}
