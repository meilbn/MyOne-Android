package com.mylody.myone.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xuzb on 10/22/14.
 */
public abstract class BasePagerAdapter extends PagerAdapter {

    private static final int MAX_VIEW_SIZE = 4;

    private View[] mViews;

    public BasePagerAdapter() {
        mViews = new View[MAX_VIEW_SIZE];
    }


    public View getView(int position) {
        return mViews[(position + MAX_VIEW_SIZE) % MAX_VIEW_SIZE];
    }

    public void setView(int position, View view) {
        int index = (position + MAX_VIEW_SIZE) % MAX_VIEW_SIZE;
        mViews[index] = view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(getView(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(position);

        View updateView = getView(view, position);

        if (updateView != view) {
            setView(position, updateView);
        }

        if (updateView.getParent() == null)
            container.addView(updateView);

        return updateView;
    }


    public void updateViewByPosition(int position) {
        View view = getView(position);
        if (view != null && view.getParent() != null) {
            getView(view, position);
        }
        notifyDataSetChanged();

    }

    public abstract View getView(View contentView, int position);


}
