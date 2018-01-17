package com.anim.animtrans;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CustomLayout extends RelativeLayout implements DragView1.LocationListener {
    private final int screenWidth, screenHeight;
    private Context context;
    private DragView1 mAutoBackView;
    public static Point mAutoBackOriginPos = new Point();
    public static Point mAutoBackOriginPosEnd = new Point();
    private int mWidth, mHeight;

    public CustomLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels; // 屏幕宽
        screenHeight = dm.heightPixels; // 屏幕高
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mAutoBackView != null) {
            mAutoBackOriginPos.x = mAutoBackView.getLeft();
            mAutoBackOriginPos.y = mAutoBackView.getTop();
            Log.d("CustomLayout", "mAutoBackOriginPos.x:" + mAutoBackOriginPos.x);
            Log.d("CustomLayout", "mAutoBackOriginPos.y:" + mAutoBackOriginPos.y);
            mWidth = mAutoBackView.getWidth();
            mHeight = mAutoBackView.getHeight();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAutoBackView = findViewById(R.id.dragview1);
        mAutoBackView.setLocationListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onActionMoveListener() {
        mAutoBackOriginPosEnd.x = mAutoBackView.getLeft();
        mAutoBackOriginPosEnd.y = mAutoBackView.getTop();
    }

    @Override
    public void onActionUpListener() {
        mAutoBackOriginPosEnd.x = mAutoBackView.getLeft();
        mAutoBackOriginPosEnd.y = mAutoBackView.getTop();

        if (Math.abs(mAutoBackOriginPosEnd.y - mAutoBackOriginPos.y) > 100) {
            Log.d("CustomLayout", "应当弹出替换窗口");
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.ic_launcher);
            RelativeLayout.LayoutParams parms = new LayoutParams(100, 100);
            parms.leftMargin = mAutoBackOriginPosEnd.x;
            parms.topMargin = mAutoBackOriginPosEnd.y;
            imageView.setLayoutParams(parms);
            addView(imageView);
        } else {
            Log.d("CustomLayout", "自动返回");
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(mAutoBackView, "translationX", -(mAutoBackOriginPosEnd.x - mAutoBackOriginPos.x)),
                    ObjectAnimator.ofFloat(mAutoBackView, "translationY", -(mAutoBackOriginPosEnd.y - mAutoBackOriginPos.y)));
            set.setInterpolator(new AccelerateInterpolator());
            set.setDuration(500).start();
        }
    }


}
