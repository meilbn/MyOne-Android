package com.mylody.myone.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mylody.myone.R;
import com.mylody.myone.databinding.ActivityMainBinding;
import com.mylody.myone.ui.fragment.HomeFragment;
import com.mylody.myone.ui.fragment.PersonalFragment;
import com.mylody.myone.ui.fragment.QuestionFragment;
import com.mylody.myone.ui.fragment.ReadingFragment;
import com.mylody.myone.ui.fragment.ThingFragment;
import com.mylody.myone.util.Utils;

public class MainActivity extends BaseActivity {

    /**
     * 对应"首页"的Fragment的TAG
     */
    private static final String TAG_PAGE_HOME = Utils.getString(R.string.main_tabbar_home_text);
    /**
     * 对应"文章"的Fragment的TAG
     */
    private static final String TAG_PAGE_READING = Utils.getString(R.string.main_tabbar_reading_text);
    /**
     * 对应"问题"的Fragment的TAG
     */
    private static final String TAG_PAGE_QUESTION = Utils.getString(R.string.main_tabbar_question_text);
    /**
     * 对应"东西"的Fragment的TAG
     */
    private static final String TAG_PAGE_THING = Utils.getString(R.string.main_tabbar_thing_text);
    /**
     * 对应"个人"的Fragment的TAG
     */
    private static final String TAG_PAGE_PERSONAL = Utils.getString(R.string.main_tabbar_personal_text);

    private String mCurrentTag;

    private ActivityMainBinding mBinding;

    TextView[] mBottomTabBarItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBottomTabBarItemList = new TextView[5];
        mBottomTabBarItemList[0] = (TextView) findViewById(R.id.mainBottomTabbarHomeTV);
        mBottomTabBarItemList[1] = (TextView) findViewById(R.id.mainBottomTabbarReadingTV);
        mBottomTabBarItemList[2] = (TextView) findViewById(R.id.mainBottomTabbarQuestionTV);
        mBottomTabBarItemList[3] = (TextView) findViewById(R.id.mainBottomTabbarThingTV);
        mBottomTabBarItemList[4] = (TextView) findViewById(R.id.mainBottomTabbarPersonalTV);
        // 默认显示首页
        setSelectBottomItem(R.id.mainBottomTabbarHomeTV);
        hideAllFragment();
        showFragment(TAG_PAGE_HOME);
    }

    /**
     * 设置当前选中的 TabBar Item
     *
     * @param viewId 选中的 Item 对应的 id
     */
    private void setSelectBottomItem(int viewId) {
        for (int i = 0; i < mBottomTabBarItemList.length; i++) {
            mBottomTabBarItemList[i].setSelected(false);
            mBottomTabBarItemList[i].setTextColor(getResources().getColor(R.color.main_tabbar_text_color));
        }
        TextView item = (TextView) findViewById(viewId);
        item.setSelected(true);
        item.setTextColor(getResources().getColor(R.color.main_tabbar_selected_text_color));
    }

    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(TAG_PAGE_HOME);
        if (homeFragment != null && !homeFragment.isHidden()) {
            transaction.hide(homeFragment);
        }

        Fragment readingFragment = getSupportFragmentManager().findFragmentByTag(TAG_PAGE_READING);
        if (readingFragment != null && !readingFragment.isHidden()) {
            transaction.hide(readingFragment);
        }

        Fragment questionFragment = getSupportFragmentManager().findFragmentByTag(TAG_PAGE_QUESTION);
        if (questionFragment != null && !questionFragment.isHidden()) {
            transaction.hide(questionFragment);
        }

        Fragment thingFragment = getSupportFragmentManager().findFragmentByTag(TAG_PAGE_THING);
        if (thingFragment != null && !thingFragment.isHidden()) {
            transaction.hide(thingFragment);
        }

        Fragment personalFragment = getSupportFragmentManager().findFragmentByTag(TAG_PAGE_PERSONAL);
        if (personalFragment != null && !personalFragment.isHidden()) {
            transaction.hide(personalFragment);
        }

        transaction.commit();
    }

    /**
     * 显示 tag 对应的 fragment
     *
     * @param tag 要显示的 fragment 对应的 tag
     */
    private void showFragment(String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isFragmentShown(transaction, tag)) {
            return;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = getFragmentInstance(tag);
            transaction.add(R.id.mainContainer, fragment, tag);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
    }

    /**
     * 判断要显示的fragment是否已经处于显示状态，不是的话会将之前的fragment隐藏
     *
     * @param transaction
     * @param newTag      要显示的fragment的标签
     * @return 已显示返回true, 否则返回false
     */
    private boolean isFragmentShown(FragmentTransaction transaction, String newTag) {
        if (newTag.equals(mCurrentTag)) {
            return true;
        }

        if (TextUtils.isEmpty(mCurrentTag)) {
            mCurrentTag = newTag;
            return false;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(mCurrentTag);
        if (fragment != null && !fragment.isHidden()) {
            transaction.hide(fragment);
        }

        mCurrentTag = newTag;

        return false;
    }

    /**
     * 根据tag得到fragment实例
     *
     * @param tag fragment对于标签
     * @return
     */
    public Fragment getFragmentInstance(String tag) {
        Fragment fragment = null;

        if (TextUtils.equals(tag, TAG_PAGE_HOME)) {
            fragment = new HomeFragment();
        } else if (TextUtils.equals(tag, TAG_PAGE_READING)) {
            fragment = new ReadingFragment();
        } else if (TextUtils.equals(tag, TAG_PAGE_QUESTION)) {
            fragment = new QuestionFragment();
        } else if (TextUtils.equals(tag, TAG_PAGE_THING)) {
            fragment = new ThingFragment();
        } else if (TextUtils.equals(tag, TAG_PAGE_PERSONAL)) {
            fragment = new PersonalFragment();
        }

        return fragment;
    }

    public void onClick(View v) {
        setSelectBottomItem(v.getId());
        switch (v.getId()) {
            case R.id.mainBottomTabbarHomeTV:
                showFragment(TAG_PAGE_HOME);
                break;
            case R.id.mainBottomTabbarReadingTV:
                showFragment(TAG_PAGE_READING);
                break;
            case R.id.mainBottomTabbarQuestionTV:
                showFragment(TAG_PAGE_QUESTION);
                break;
            case R.id.mainBottomTabbarThingTV:
                showFragment(TAG_PAGE_THING);
                break;
            case R.id.mainBottomTabbarPersonalTV:
                showFragment(TAG_PAGE_PERSONAL);
                break;
        }
    }

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
