package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylody.myone.R;
import com.mylody.myone.ui.activity.AboutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {



    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @OnClick(R.id.personal_login_rl)
    public void onClickLogin() {

    }

    @OnClick(R.id.personal_settings_rl)
    public void onClickSettings() {

    }

    @OnClick(R.id.personal_about_rl)
    public void onClickAbout() {
        AboutActivity.openActivity(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
