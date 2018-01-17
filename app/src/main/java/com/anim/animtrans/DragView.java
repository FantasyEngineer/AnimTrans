/*
package com.anim.animtrans;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

*/
/**
 * Created by Administrator on 2018/1/16.
 *//*


public class DragView extends android.support.v7.widget.AppCompatImageView {
    private float moveX;
    private float moveY;

    private LocationListener locationListener;
    private float upX, upY;
    private int oriLeft, oriTop;

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
                upX = getX() + (event.getX() - moveX);
                upY = getY() + (event.getY() - moveY);
                if (locationListener != null) {
                    locationListener.onActionMoveListener(upX, upY);
                }
                //操作view的位移
                setTranslationX(event.getX());
                setTranslationY(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                if (locationListener != null) {
                    locationListener.onActionUpListener(upX, upY);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        oriLeft = getLeft();
        oriTop = getTop();
    }

    interface LocationListener {
        void onActionMoveListener(float movex, float movey);

        void onActionUpListener(float upx, float upy);

    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }
}
*/
