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
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.module.ReadingModel;
import com.mylody.myone.ui.adapter.ReadingItemAdapter;
import com.mylody.myone.util.Constants;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment implements PullToRefreshBase.OnRefreshListener,
        ReadingItemAdapter.OnLoadingListener, ReadingModel.ReadingCallback {

    private PullToRefreshViewPager mPullToRefreshViewPager;
    private ViewPager mViewPager;
    private ReadingItemAdapter mAdapter;

    private ReadingModel mModel;

    private boolean isLoading = false;
    private int mCurrentItem;

    public ReadingFragment() {
        long timeMillis = System.currentTimeMillis();
        mModel = new ReadingModel(timeMillis);
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reading, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPullToRefreshViewPager = (PullToRefreshViewPager) view.findViewById(R.id.readingRefreshViewPager);
        mPullToRefreshViewPager.setOnRefreshListener(this);
        mViewPager = mPullToRefreshViewPager.getRefreshableView();
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mAdapter = new ReadingItemAdapter(getActivity(), mViewPager);
        mAdapter.setOnLoadingListener(this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mCurrentItem != position && positionOffset == 0 && mAdapter.dateIsEmpty(position)) {
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
    public void onLoading(final int position) {
        if (isLoading) {
            Timber.d("正在加载中...");
            return;
        }
        isLoading = true;
        mModel.getReadingContentInfo(position, ReadingFragment.this);

    }

    @Override
    public void getReadingContentInfoSuccess(int position, ReadingBean bean) {
        isLoading = false;
        if (mPullToRefreshViewPager.isRefreshing()) {
            mPullToRefreshViewPager.onRefreshComplete();
            mAdapter.updateViewByPosition(position + 1);
        }

        int currentItem = mViewPager.getCurrentItem();

        mAdapter.addItem(position, bean);

        mAdapter.updateViewByPosition(position == currentItem + 1 ? position : currentItem);
//        mAdapter.updateViewByPosition(currentItem);
    }

    @Override
    public void getReadingContentInfoError(int position) {
        isLoading = false;

        int currentItem = mViewPager.getCurrentItem();

        ReadingBean readingBean = new ReadingBean();

        readingBean.setResult(Constants.REQUEST_ERROR);

        mAdapter.addItem(position, readingBean);

        mAdapter.updateViewByPosition(position == currentItem + 1 ? position : currentItem);
//        mAdapter.updateViewByPosition(currentItem);
    }

}
