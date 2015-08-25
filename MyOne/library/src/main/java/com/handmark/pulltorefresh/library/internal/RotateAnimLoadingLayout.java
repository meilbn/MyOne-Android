package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * User:Shine
 * Date:2015-08-25
 * Description:
 */
public class RotateAnimLoadingLayout extends LoadingLayout {
    private AnimationDrawable animationDrawable;
    private final Matrix mHeaderImageMatrix;
    private final boolean mRotateDrawableWhilePulling;
    private float mRotationPivotX;
    private float mRotationPivotY;

    public RotateAnimLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        this.mRotateDrawableWhilePulling = attrs.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);
        this.mHeaderImage.setScaleType(ImageView.ScaleType.MATRIX);
        this.mHeaderImageMatrix = new Matrix();
        this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
    }

    protected int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
        if (null != imageDrawable) {
            mRotationPivotX = Math.round(imageDrawable.getIntrinsicWidth() / 2f);
            mRotationPivotY = Math.round(imageDrawable.getIntrinsicHeight() / 2f);
        }
    }

    protected void onPullImpl(float scaleOfLayout) {
        float angle;
        if (this.mRotateDrawableWhilePulling) {
            angle = scaleOfLayout * 90f;
        } else {
            angle = Math.max(0f, Math.min(180f, scaleOfLayout * 360f - 180f));
        }
        mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
        mHeaderImage.setImageMatrix(mHeaderImageMatrix);

    }

    protected void refreshingImpl() {
        if (this.animationDrawable == null) {
            this.animationDrawable = ((AnimationDrawable) getResources().getDrawable(R.anim.anim_loading));
        }
        this.mHeaderImage.setImageDrawable(this.animationDrawable);
        this.animationDrawable.start();
    }

    protected void resetImpl() {
        if (this.animationDrawable == null) {
            this.animationDrawable = ((AnimationDrawable) getResources().getDrawable(R.anim.anim_loading));
        }
        this.mHeaderImage.setImageDrawable(this.animationDrawable);
        this.animationDrawable.stop();
    }

    protected void pullToRefreshImpl() {
    }

    protected void releaseToRefreshImpl() {
    }


}
