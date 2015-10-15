package com.mylody.myone.ui.fragment;


import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mylody.myone.R;
import com.mylody.myone.bean.HomeBean;
import com.mylody.myone.databinding.FragmentHomeItemBinding;
import com.mylody.myone.module.HomeModel;
import com.mylody.myone.util.Utils;

/**
 * User: HappyHacking
 * Date: 2015-08-26
 * Time: 14:35
 * Description:
 */
public class HomeItemFragment extends Fragment implements HomeModel.HomeCallback {
    private static final String ARGS_ITEM_POSITION = "com.mylody.myone.itemPosition";
    private static final String ARGS_TIME_MILLIS = "com.mylody.myone.timeMillis";

    private FragmentHomeItemBinding mItemBinding;
    private HomeModel mModel;
    private int mItemPosition;

    public HomeItemFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(int position, long timeMillis) {
        Fragment fragment = new HomeItemFragment();
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
        mModel = new HomeModel(timeMillis);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_item, container, false);
        return mItemBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mModel.getOneHomeEntity(mItemPosition, this);
    }

    /**
     * 获取首页内容成功
     * @param bean
     */
    @Override
    public void getOneHomeEntitySuccess(HomeBean bean) {
        // 显示布局
        mItemBinding.homePageItemLinearLayout.setVisibility(View.VISIBLE);
        HomeBean.HpEntity hpEntity = bean.getHpEntity();
        // 分离画的名字和作者
        String paintInfo = hpEntity.getStrAuthor();
        String paintName = paintInfo.split("&")[0];
        hpEntity.setStrPaintName(paintName);
        String paintAuthor = paintInfo.split("&")[1];
        hpEntity.setStrAuthor(paintAuthor);
        // 转换时间字符串并分离日和年月
        String marketDate = Utils.convertMarketTimeForHome(hpEntity.getStrMarketTime());
        if (!TextUtils.isEmpty(marketDate)) {
            hpEntity.setStrDay(marketDate.split(" ")[0]);
            hpEntity.setStrMonthYear(marketDate.split(" ")[1]);
        }

        // 设置画的宽高
        ViewGroup.LayoutParams layoutParams = mItemBinding.homePageItemPaintIV.getLayoutParams();
        int paintWidth = Utils.getScreenWidth() - 30;
        int paintHeight = (int) (paintWidth * 0.75);
        layoutParams.width = paintWidth;
        layoutParams.height = paintHeight;
        mItemBinding.homePageItemPaintIV.setLayoutParams(layoutParams);

        ImageView paint = mItemBinding.homePageItemPaintIV;
        // 加载画的图片
        Glide.with(getActivity()).load(hpEntity.getStrThumbnailUrl()).into(paint);

        mItemBinding.setHpEntity(hpEntity);
    }

    /**
     * 获取首页内容失败
     */
    @Override
    public void getOneHomeEntityFailed() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.cancelAll();
        }

        super.onDestroy();
    }
}
