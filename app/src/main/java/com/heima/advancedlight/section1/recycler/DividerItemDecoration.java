package com.heima.advancedlight.section1.recycler;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作 者：黑马小杰
 * 时 间：2018/3/18
 * 邮 箱：598214297@qq.com
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {

        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }


    /**
     * 竖直排列，画水平分割线
     *
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        /*  当orientation为 Vertical 时，Item的分割线水平线条
            分割线的 Top 和 Bottom 则需要计算出有多少个Item
            首先由Item的位置获取到child的位置坐标*/

        //左边=item左边距
        final int left = parent.getPaddingLeft();
        //右边的坐标=右边 + 分割线的width
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //Top = child的下边坐标 + child外边距距离
            final int top = child.getBottom() + params.bottomMargin;
            // 然后根据bottom = top +分割线高度
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 水平排列，画竖直分割线
     *
     * @param c
     * @param parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
            /*当orientation为 Horizontal 时，Item的分割线为竖直线
            分割线的Top和Bottom就容易确定
            分割线的 left 和 right 则需要计算出有多少个Item
            根据Item的位置获取到child的位置坐标*/

        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //所以分割线的left = child的右边的坐标 + child的外边距的距离
            final int left = child.getRight() + params.rightMargin;
            //然后根据左边 + 分割线的宽度 得到右边的坐标
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    /* outRect.set(left, top, right, bottom);
       Item的四周间距
       垂直时，Item的下方预留出分割线的高度
       水平时，Item的右方预留出分割线的宽度*/
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}