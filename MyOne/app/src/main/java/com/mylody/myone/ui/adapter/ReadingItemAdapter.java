package com.mylody.myone.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mylody.myone.R;
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.databinding.ItemReadingBinding;
import com.mylody.myone.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * User:Shine
 * Date:2015-10-14
 * Description:
 */
public class ReadingItemAdapter extends BasePagerAdapter implements View.OnClickListener {

    private final LayoutInflater mInflater;
    private final List<ReadingBean> mData;
    private final ViewPager mViewPager;
    private OnLoadingListener mOnLoadingListener;


    public ReadingItemAdapter(Context context, ViewPager viewPager) {
        mInflater = LayoutInflater.from(context);
        mViewPager = viewPager;
        mData = new ArrayList<>();
    }


    @Override
    public View getView(View contentView, int position) {
        ViewHolder holder;
        if (contentView == null) {
            ItemReadingBinding dataBinding = DataBindingUtil.inflate(mInflater, R.layout.item_reading, null, false);
            contentView = dataBinding.getRoot();
            holder = new ViewHolder(contentView, dataBinding);

            holder.btnRetry.setOnClickListener(this);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        if (position < mData.size()) {
            ReadingBean readingBean = mData.get(position);
            ReadingBean.ContentEntity contentEntity = readingBean.getContentEntity();
            if (contentEntity != null) {//设置内容
                holder.mDataBinding.setContent(contentEntity);

                holder.mDataBinding.scrollView.setVisibility(View.VISIBLE);
                holder.pbLoading.setVisibility(View.GONE);

                holder.mDataBinding.scrollView.scrollTo(0, 0);


            } else if (readingBean.getResult().equals(Constants.REQUEST_ERROR)) {//加载错误，显示重新加载
                holder.mDataBinding.scrollView.setVisibility(View.GONE);
                holder.pbLoading.setVisibility(View.GONE);
                holder.llLoadingFailure.setVisibility(View.VISIBLE);
                holder.btnRetry.setTag(position);
            } else if (readingBean.getResult().equals(Constants.REQUEST_LOADING)) {//需要通过网络请求数据
                loading(position, holder);
            }

        } else {//需要通过网络请求数据
            loading(position, holder);
        }

        return contentView;
    }

    private void loading(int position, ViewHolder holder) {
        holder.mDataBinding.scrollView.setVisibility(View.GONE);
        holder.llLoadingFailure.setVisibility(View.GONE);
        holder.pbLoading.setVisibility(View.VISIBLE);

        if (position <= mViewPager.getCurrentItem() && mOnLoadingListener != null) {
            mOnLoadingListener.onLoading(position);
        }
    }


    @Override
    public int getCount() {
        return mData.size() + 1;
    }

    public void clearData() {
        mData.clear();
    }

    public void addItem(int position, ReadingBean bean) {
        if (position < mData.size()) {
            ReadingBean readingBean = mData.get(position);
            if (readingBean != null && readingBean.getContentEntity() == null) {
                readingBean.setResult(Constants.REQUEST_SUCCESS);
                readingBean.setContentEntity(bean.getContentEntity());
            }
        } else {
            mData.add(bean);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetry) {
            int position = (int) v.getTag();
            ReadingBean readingBean = mData.get(position);
            readingBean.setResult(Constants.REQUEST_LOADING);
            updateViewByPosition(position);
        }
    }

    public boolean dataIsEmpty(int position) {
        return position >= mData.size();
    }


    public static class ViewHolder {

        ItemReadingBinding mDataBinding;

        private ProgressBar pbLoading;
        private Button btnRetry;
        private View llLoadingFailure;

        public ViewHolder(View contentView, ItemReadingBinding dataBinding) {
            this.mDataBinding = dataBinding;

            this.pbLoading = (ProgressBar) contentView.findViewById(R.id.pbLoading);
            this.btnRetry = (Button) contentView.findViewById(R.id.btnRetry);
            this.llLoadingFailure = contentView.findViewById(R.id.llLoadingFailure);
        }
    }

    public interface OnLoadingListener {

        void onLoading(int position);

    }


    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        mOnLoadingListener = onLoadingListener;
    }
}
