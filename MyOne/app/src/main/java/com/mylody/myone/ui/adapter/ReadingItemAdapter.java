package com.mylody.myone.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.mylody.myone.R;
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.databinding.ItemReadingBinding;
import com.mylody.myone.util.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * User:Shine
 * Date:2015-10-14
 * Description:
 */
public class ReadingItemAdapter extends BaseAdapter implements View.OnClickListener {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<ReadingBean> mData;
    private final ViewPager mViewPager;
    private OnLoadingListener mOnLoadingListener;


    public ReadingItemAdapter(Context context, ViewPager viewPager) {
        mContext = context;
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
            if (contentEntity != null) {
                holder.mDataBinding.setContent(contentEntity);

                holder.mDataBinding.scrollView.setVisibility(View.VISIBLE);
                holder.pbLoading.setVisibility(View.GONE);

                holder.mDataBinding.scrollView.scrollTo(0, 0);
                holder.mDataBinding.tvContent.setText(Html.fromHtml(contentEntity.getStrContent()));
                SpannableString spannableString = new SpannableString(contentEntity.getStrContAuthor() + " " + contentEntity.getSWbN());
                //得到要改变颜色已经大小的文字开始位置
                int spanStart = spannableString.toString().indexOf(contentEntity.getSWbN());
                //改变文字大小
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //改变文字颜色
                spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.dis_hint_text)), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.mDataBinding.tvAuthorAndWbN.setText(spannableString);
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

    public boolean dateIsEmpty(int position) {
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
