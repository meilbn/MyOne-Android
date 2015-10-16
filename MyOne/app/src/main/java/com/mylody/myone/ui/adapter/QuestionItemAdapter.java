package com.mylody.myone.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mylody.myone.R;
import com.mylody.myone.bean.QuestionBean;
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.databinding.ItemQuestionBinding;
import com.mylody.myone.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * User:Shine
 * Date:2015-10-16
 * Description:
 */
public class QuestionItemAdapter extends BasePagerAdapter implements View.OnClickListener {

    private final LayoutInflater mInflater;
    private final List<QuestionBean> mData;
    private final ViewPager mViewPager;
    private OnLoadingListener mOnLoadingListener;

    public QuestionItemAdapter(Context context, ViewPager viewPager) {
        mInflater = LayoutInflater.from(context);
        mViewPager = viewPager;
        mData = new ArrayList<>();
    }


    @Override
    public View getView(View contentView, int position) {
        ViewHolder holder;
        if (contentView == null) {
            ItemQuestionBinding dataBinding = DataBindingUtil.inflate(mInflater, R.layout.item_question, null, false);
            contentView = dataBinding.getRoot();
            holder = new ViewHolder(contentView, dataBinding);
            holder.btnRetry.setOnClickListener(this);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        if (position < mData.size()) {
            QuestionBean questionBean = mData.get(position);
            QuestionBean.QuestionAdEntity questionAdEntity = questionBean.getQuestionAdEntity();
            if (questionAdEntity != null) {//设置内容
                holder.mDataBinding.setQuestion(questionAdEntity);

                holder.mDataBinding.scrollView.setVisibility(View.VISIBLE);
                holder.pbLoading.setVisibility(View.GONE);

                holder.mDataBinding.scrollView.scrollTo(0, 0);


            } else if (questionBean.getResult().equals(Constants.REQUEST_ERROR)) {//加载错误，显示重新加载
                holder.mDataBinding.scrollView.setVisibility(View.GONE);
                holder.pbLoading.setVisibility(View.GONE);
                holder.llLoadingFailure.setVisibility(View.VISIBLE);
                holder.btnRetry.setTag(position);
            } else if (questionBean.getResult().equals(Constants.REQUEST_LOADING)) {//需要通过网络请求数据
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

    public boolean dataIsEmpty(int position) {
        return position >= mData.size();
    }

    public void clearData() {
        mData.clear();
    }

    public void addItem(int position, QuestionBean bean) {
        if (position < mData.size()) {
            QuestionBean questionBean = mData.get(position);
            if (questionBean != null && questionBean.getQuestionAdEntity() == null) {
                questionBean.setResult(Constants.REQUEST_SUCCESS);
                questionBean.setQuestionAdEntity(bean.getQuestionAdEntity());
            }
        } else {
            mData.add(bean);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetry) {
            int position = (int) v.getTag();
            QuestionBean readingBean = mData.get(position);
            readingBean.setResult(Constants.REQUEST_LOADING);
            updateViewByPosition(position);
        }
    }


    public static class ViewHolder {

        ItemQuestionBinding mDataBinding;

        private ProgressBar pbLoading;
        private Button btnRetry;
        private View llLoadingFailure;

        public ViewHolder(View contentView, ItemQuestionBinding dataBinding) {
            this.mDataBinding = dataBinding;

            this.pbLoading = (ProgressBar) contentView.findViewById(R.id.pbLoading);
            this.btnRetry = (Button) contentView.findViewById(R.id.btnRetry);
            this.llLoadingFailure = contentView.findViewById(R.id.llLoadingFailure);
        }

    }

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        mOnLoadingListener = onLoadingListener;
    }
}
