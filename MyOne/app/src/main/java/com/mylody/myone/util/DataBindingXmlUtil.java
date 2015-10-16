package com.mylody.myone.util;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.mylody.myone.R;
import com.mylody.myone.application.MyOneApplication;
import com.mylody.myone.bean.ReadingBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User:Shine
 * Date:2015-10-16
 * Description:
 */
public class DataBindingXmlUtil {

    /*文章模块的正文内容*/
    @BindingAdapter("htmlText")
    public static void setHtmlText(TextView textView, String htmlText) {
        if (textView != null && htmlText != null)
            textView.setText(Html.fromHtml(htmlText));
    }

    /*文章模块的正文内容*/
    @BindingAdapter("usDate")
    public static void setDateByUS(TextView textView, String marketTime) {
        if (textView != null && marketTime != null) {
            try {
                SimpleDateFormat formatString = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date marketDate = formatString.parse(marketTime);
                SimpleDateFormat formatDate = new SimpleDateFormat("MMMM dd,yyyy", Locale.US);
                String convertedMarketTime = formatDate.format(marketDate);
                textView.setText(convertedMarketTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /*文章模块的作者以及@文字*/
    @BindingAdapter("authorAndWbN")
    public static void setReadingAuthorAndWbN(TextView textView, ReadingBean.ContentEntity contentEntity) {
        if (textView == null || contentEntity == null) return;

        SpannableString spannableString = new SpannableString(contentEntity.getStrContAuthor() + " " + contentEntity.getSWbN());
        //得到要改变颜色已经大小的文字开始位置
        int spanStart = spannableString.toString().indexOf(contentEntity.getSWbN());
        //改变文字大小
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //改变文字颜色
        spannableString.setSpan(new ForegroundColorSpan(MyOneApplication.getContext().getResources().getColor(R.color.dis_hint_text)), spanStart, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
