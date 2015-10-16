package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.extras.viewpager.PullToRefreshViewPager;
import com.mylody.myone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    private PullToRefreshViewPager mPullToRefreshViewPager;
    private ViewPager mViewPager;


    public QuestionFragment() {
        // Required empty public constructor
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
//        mPullToRefreshViewPager.setOnRefreshListener(this);
        mViewPager = mPullToRefreshViewPager.getRefreshableView();
//        mViewPager.setAdapter(new QuestionItemAdapter(getActivity().getSupportFragmentManager()));

    }




}
