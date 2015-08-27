package com.mylody.myone.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mylody.myone.R;
import com.mylody.myone.bean.ThingBean;
import com.mylody.myone.databinding.FragmentThingItemBinding;
import com.mylody.myone.module.ThingModel;
import com.mylody.myone.util.Constants;
import com.mylody.myone.util.Utils;

import java.util.Timer;

/**
 * User: HappyHacking
 * Date: 2015-08-27
 * Time: 17:10
 * Description:
 */
public class ThingItemFragment extends Fragment implements ThingModel.ThingCallback {
    private static final String ARGS_ITEM_POSITION = "com.mylody.myone.itemPosition";
    private static final String ARGS_TIME_MILLIS = "com.mylody.myone.timeMillis";

    private FragmentThingItemBinding mItemBinding;
    private ThingModel mModel;
    private int mItemPosition;

    public ThingItemFragment() {
        // Required empty public constructor
    }

    public static android.support.v4.app.Fragment newInstance(int position, long timeMillis) {
        Fragment fragment = new ThingItemFragment();
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
        mModel = new ThingModel(timeMillis);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_thing_item, container, false);
        return mItemBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mModel.getOneThingEntity(mItemPosition, this);
    }

    /**
     * 获取首页内容成功
     * @param bean
     */
    @Override
    public void getOneThingEntitySuccess(ThingBean bean) {
        // 显示布局
        mItemBinding.thingPageItemLinearLayout.setVisibility(View.VISIBLE);
        ThingBean.EntTg entTg = bean.getEntTg();
        // 转换时间字符串
        String marketDate = Utils.convertMarketTimeForHomeElse(entTg.getStrTm());
        if (!TextUtils.isEmpty(marketDate)) {
            entTg.setStrMarketTime(marketDate);
        }

        Log.d(Constants.HappyHackingTAG, "marketDate = " + marketDate);

        // 设置画的宽高
        ViewGroup.LayoutParams layoutParams = mItemBinding.thingPageItemImageIV.getLayoutParams();
        int imageWidth = Utils.getScreenWidth() - 30;
        int imageHeight = imageWidth;
        layoutParams.width = imageWidth;
        layoutParams.height = imageHeight;
        Log.d(Constants.HappyHackingTAG, "imageWidth = " + imageWidth + " imageHeight = " + imageHeight);
        mItemBinding.thingPageItemImageIV.setLayoutParams(layoutParams);

        ImageView imageView = mItemBinding.thingPageItemImageIV;
        // 加载画的图片
        Glide.with(getActivity()).load(entTg.getStrBu()).into(imageView);

        mItemBinding.setEntTg(entTg);
    }

    /**
     * 获取首页内容失败
     */
    @Override
    public void getOneThingEntityFailed() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.cancelAll();
        }

        super.onDestroy();
    }
}
