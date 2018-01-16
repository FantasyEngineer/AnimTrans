package com.anim.animtrans;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/1/16.
 */

public class CustomLayout extends RelativeLayout {
    private Context context;
    private View mAutoBackView;
    private Point mAutoBackOriginPos = new Point();
    float moveX;
    float moveY;
    private int mLeft;
    public boolean mNeedLayout;
    public int mBottom;
    public int mRight;
    public int mTop;

    public CustomLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                setTranslationX(getX() + (event.getX() - moveX));
//                setTranslationY(getY() + (event.getY() - moveY));
//                mAutoBackView.layout((int) (getX() + (event.getX() - moveX)), (int) (getY() + (event.getY() - moveY)), (int) (getX() + (event.getX() - moveX) + mAutoBackView.getWidth()), (int) (getY() + (event.getY() - moveY) + mAutoBackView.getHeight()));
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }


        return true;
    }

    @Override
    public void computeScroll() {
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mAutoBackView != null) {
            mAutoBackOriginPos.x = mAutoBackView.getLeft();
            mAutoBackOriginPos.y = mAutoBackView.getTop();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAutoBackView = findViewById(R.id.new_item);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
