package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylody.myone.R;

/**
 * User: HappyHacking
 * Date: 2015-08-26
 * Time: 14:35
 * Description:
 */
public class HomeItemFragment extends Fragment {


    public HomeItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_item, container, false);
    }


}
