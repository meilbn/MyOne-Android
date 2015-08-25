package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylody.myone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment {


    private ViewPager mViewPager;

    public ReadingFragment() {
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

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mViewPager.setAdapter(new ReadingItemAdapter(getActivity().getSupportFragmentManager()));

    }


    private static class ReadingItemAdapter extends FragmentPagerAdapter {

        private Fragment[] mFragment;

        public ReadingItemAdapter(FragmentManager fm) {
            super(fm);
            mFragment = new Fragment[10];
            for (int i = 0; i < mFragment.length; i++) {
                mFragment[i] = new ReadingItemFragment();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment[position];
        }

        @Override
        public int getCount() {
            return mFragment.length;
        }
    }

}
