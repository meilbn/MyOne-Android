package com.mylody.myone.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mylody.myone.R;
import com.mylody.myone.ui.activity.AboutActivity;
import com.mylody.myone.ui.activity.SettingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mLoginRL;
    private RelativeLayout mSettingsRL;
    private RelativeLayout mAboutRL;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        mLoginRL = (RelativeLayout) view.findViewById(R.id.personalLoginRL);
        mSettingsRL = (RelativeLayout) view.findViewById(R.id.personalSettingsRL);
        mAboutRL = (RelativeLayout) view.findViewById(R.id.personalAboutRL);
        mLoginRL.setOnClickListener(this);
        mSettingsRL.setOnClickListener(this);
        mAboutRL.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalLoginRL: {
                onClickLogin();
                break;
            }
            case R.id.personalSettingsRL: {
                onClickSettings();
                break;
            }
            case R.id.personalAboutRL: {
                onClickAbout();
                break;
            }
        }
    }

    public void onClickLogin() {

    }

    public void onClickSettings() {
        SettingActivity.openActivity(getActivity());
    }

    public void onClickAbout() {
        AboutActivity.openActivity(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
