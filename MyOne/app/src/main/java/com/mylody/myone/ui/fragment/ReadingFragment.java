package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.viewpager.PullToRefreshViewPager;
import com.mylody.myone.R;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2 {

    private final long mCurrentTimeMillis;

    private PullToRefreshViewPager mPullToRefreshViewPager;
    private ViewPager mViewPager;

    public ReadingFragment() {
        mCurrentTimeMillis = System.currentTimeMillis();
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
        mViewPager.setAdapter(new ReadingItemAdapter(getActivity().getSupportFragmentManager()));

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Timber.d("最左");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshViewPager.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Timber.d("最右");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshViewPager.onRefreshComplete();
            }
        }, 1000);
    }


    private class ReadingItemAdapter extends FragmentStatePagerAdapter {

        private Fragment[] mFragments;

        public ReadingItemAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new Fragment[10];
        }

        @Override
        public Fragment getItem(int position) {
            if (mFragments[position] == null) {
                mFragments[position] = ReadingItemFragment.newInstance(position, mCurrentTimeMillis);
            }
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

}
