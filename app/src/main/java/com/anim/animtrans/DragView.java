package com.anim.animtrans;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2018/1/16.
 */

public class DragView extends android.support.v7.widget.AppCompatTextView {
    float originalX, originalY;
    int[] original = new int[2];
    float moveX;
    float moveY;
    private int mLeft;
    public boolean mNeedLayout;
    public int mBottom;
    public int mRight;
    public int mTop;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
                setTranslationX(originalX);
                setTranslationY(originalY);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        originalX = getLeft();
        originalY = getTop();
    }


    /**
     * 动画移动view并摆放至相应的位置
     *
     * @param view               控件
     * @param xFromDeltaDistance x起始位置的偏移量
     * @param xToDeltaDistance   x终止位置的偏移量
     * @param yFromDeltaDistance y起始位置的偏移量
     * @param yToDeltaDistance   y终止位置的偏移量
     * @param duration           动画的播放时间
     * @param delay              延迟播放时间
     * @param isBack             是否需要返回到开始位置
     */
    public void moveViewWithAnimation(final View view, final float xFromDeltaDistance, final float xToDeltaDistance, final float yFromDeltaDistance, final float yToDeltaDistance, int duration, int delay, final boolean isBack) {
        //创建位移动画
        TranslateAnimation ani = new TranslateAnimation(xFromDeltaDistance, xToDeltaDistance, yFromDeltaDistance, yToDeltaDistance);
        ani.setInterpolator(new OvershootInterpolator());//设置加速器
        ani.setDuration(duration);//设置动画时间
        ani.setStartOffset(delay);//设置动画延迟时间
        //监听动画播放状态
        ani.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int deltaX = (int) (xToDeltaDistance - xFromDeltaDistance);
                int deltaY = (int) (yToDeltaDistance - yFromDeltaDistance);
                int layoutX = view.getLeft();
                int layoutY = view.getTop();
                int tempWidth = view.getWidth();
                int tempHeight = view.getHeight();
                view.clearAnimation();
                if (isBack == false) {
                    layoutX += deltaX;
                    layoutY += deltaY;
                    view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
                } else {
                    view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
                }
                mLeft = layoutX;
                mTop = layoutY;
                mRight = layoutX + tempWidth;
                mBottom = layoutY + tempHeight;
                mNeedLayout = true;
            }
        });
        view.startAnimation(ani);
    }
}
