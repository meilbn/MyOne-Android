package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.viewpager.PullToRefreshViewPager;
import com.mylody.myone.R;
import com.mylody.myone.bean.QuestionBean;
import com.mylody.myone.module.QuestionModel;
import com.mylody.myone.ui.adapter.OnLoadingListener;
import com.mylody.myone.ui.adapter.QuestionItemAdapter;
import com.mylody.myone.util.Constants;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements OnLoadingListener, PullToRefreshBase.OnRefreshListener, QuestionModel.QuestionCallback {


    private PullToRefreshViewPager mPullToRefreshViewPager;
    private ViewPager mViewPager;

    private QuestionItemAdapter mAdapter;

    private QuestionModel mModel;

    private boolean isLoading = false;
    private int mCurrentItem;

    public QuestionFragment() {
        // Required empty public constructor
        long timeMillis = System.currentTimeMillis();
        mModel = new QuestionModel(timeMillis);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mPullToRefreshViewPager = (PullToRefreshViewPager) view.findViewById(R.id.questionRefreshViewPager);
        mPullToRefreshViewPager.setOnRefreshListener(this);
        mViewPager = mPullToRefreshViewPager.getRefreshableView();
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mAdapter = new QuestionItemAdapter(getActivity(), mViewPager);
        mAdapter.setOnLoadingListener(this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mCurrentItem != position && positionOffset == 0 && mAdapter.dataIsEmpty(position)) {
                    Timber.i("update page");
                    mCurrentItem = position;
                    mAdapter.updateViewByPosition(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onLoading(int position) {
        if (isLoading) {
            Timber.d("正在加载中...");
            return;
        }
        isLoading = true;
        mModel.getQuestionInfo(position, QuestionFragment.this);
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        long oldTimeMillis = mModel.getTimeMillis();
        long newTimeMillis = System.currentTimeMillis();

        if (!mModel.getDateByTimeMillis(newTimeMillis).equals(mModel.getDateByTimeMillis(oldTimeMillis))) {
            mModel.setTimeMillis(newTimeMillis);

            mAdapter.clearData();
            mAdapter.updateViewByPosition(mViewPager.getCurrentItem());
        } else {
            mPullToRefreshViewPager.onRefreshComplete();
        }

    }

    @Override
    public void getQuestionInfoSuccess(int position, QuestionBean bean) {
        isLoading = false;
        if (mPullToRefreshViewPager.isRefreshing()) {
            mPullToRefreshViewPager.onRefreshComplete();
            mAdapter.updateViewByPosition(position + 1);
        }

        int currentItem = mViewPager.getCurrentItem();

        mAdapter.addItem(position, bean);

        mAdapter.updateViewByPosition(position == currentItem + 1 ? position : currentItem);
    }

    @Override
    public void getQuestionInfoError(int position) {
        isLoading = false;

        int currentItem = mViewPager.getCurrentItem();

        QuestionBean questionBean = new QuestionBean();

        questionBean.setResult(Constants.REQUEST_ERROR);

        mAdapter.addItem(position, questionBean);

        mAdapter.updateViewByPosition(position == currentItem + 1 ? position : currentItem);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mModel != null)
            mModel.cancelAll();
    }
}
