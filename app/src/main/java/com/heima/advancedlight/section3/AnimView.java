package com.heima.advancedlight.section3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * ObjectAnimator反射机制
 * 包装类   set/get方法必须
 */
public class AnimView {
    private View mTarget;

    public AnimView(View mTarget) {
        this.mTarget = mTarget;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
