package com.mylody.myone.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylody.myone.R;
import com.mylody.myone.bean.ReadingBean;
import com.mylody.myone.databinding.FragmentReadingItemBinding;
import com.mylody.myone.module.ReadingModel;
import com.mylody.myone.util.Utils;

/**
 * User:Shine
 * Date:2015-08-24
 * Description:
 */
public class ReadingItemFragment extends Fragment implements ReadingModel.ReadingCallback {

    private static final String ARGS_ITEM_POSITION = "com.mylody.myone.itemPosition";
    private static final String ARGS_TIME_MILLIS = "com.mylody.myone.timeMillis";

    private ReadingModel mModel;

    private int mItemPosition;

    private FragmentReadingItemBinding mItemBinding;

    public static Fragment newInstance(int position, long timeMillis) {
        Fragment fragment = new ReadingItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_ITEM_POSITION, position);
        bundle.putLong(ARGS_TIME_MILLIS, timeMillis);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mItemPosition = bundle.getInt(ARGS_ITEM_POSITION);
        long timeMillis = bundle.getLong(ARGS_TIME_MILLIS);
        mModel = new ReadingModel(timeMillis);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reading_item, container, false);
        return mItemBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mModel.getOneContentInfo(mItemPosition, this);

    }

    /*获取文章成功*/
    @Override
    public void getOneContentInfoSuccess(ReadingBean bean) {
        mItemBinding.llContentParent.setVisibility(View.VISIBLE);
        ReadingBean.ContentEntity contentEntity = bean.getContentEntity();
        mItemBinding.setContent(contentEntity);
        mItemBinding.tvContent.setText(Html.fromHtml(contentEntity.getStrContent()));

        SpannableString spannableString = new SpannableString(contentEntity.getStrContAuthor() + " " + contentEntity.getSWbN());
        //得到要改变颜色已经大小的文字开始位置
        int spanStart = spannableString.toString().indexOf(contentEntity.getSWbN());
        //改变文字大小
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //改变文字颜色
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dis_hint_text)), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mItemBinding.tvAuthorAndWbN.setText(spannableString);
//        tvAuthorAndWbN
    }

    /*获取文章失败*/
    @Override
    public void getOneContentInfoError() {

    }


    @Override
    public void onDestroy() {
        if (mModel != null) mModel.cancelAll();
        super.onDestroy();
    }
}
