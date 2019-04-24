package com.heima.advancedlight.section3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * View的移动
 */
public class MoveView extends View {
    int lastX, lastY;
    Scroller mScroller;

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
         mScroller=new Scroller(context);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int moveX = x - lastX;
                int moveY = y - lastY;

              /*  layout(getLeft() + moveX, getTop() + moveY,
                        getRight() + moveX, getBottom() + moveY);*/
             /*   offsetLeftAndRight(moveX);
                offsetTopAndBottom(moveY);*/

               ((View)this.getParent()).scrollBy(-moveX,-moveY);

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            ((View)this.getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 平滑移动
     * @param dx 偏移
     * @param dy
     */
    public  void smoothScroller(int dx,int dy){
        int scrollerX=getScrollX();
        int deltaX=dx-scrollerX;
        int scrollerY=getScrollY();
        int deltaY=dy-scrollerY;
        mScroller.startScroll(scrollerX,scrollerY,deltaX,deltaY,20000);

invalidate();
    }
}
