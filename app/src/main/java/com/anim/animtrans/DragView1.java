package com.anim.animtrans;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by liwen on 2016/11/2.
 */
public class DragView1 extends View {

    private int lastY;
    private int lastX;
    private RelativeLayout.LayoutParams mParams;
    private int offsetX;
    private Context mContext;
    private int offsetY;

    public DragView1(Context context) {
        this(context, null);
    }

    public DragView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        setBackgroundResource(R.mipmap.drag);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d("DragView1", "x:" + x);
        Log.d("DragView1", "y:" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
//                Log.d("DragView1", "lastX:" + lastX);
//                Log.d("DragView1", "lastY:" + lastX);
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = getLeft() + x - lastX;
                offsetY = getTop() + y - lastY;
                if (locationListener != null) {
                    locationListener.onActionMoveListener();
                }
                layout(offsetX, offsetY, offsetX + getWidth(), offsetY + getHeight());
                break;
            case MotionEvent.ACTION_UP:
                if (locationListener != null) {
                    locationListener.onActionUpListener();
                }
                break;
        }

        return true;

    }


    private LocationListener locationListener;


    interface LocationListener {
        void onActionMoveListener();

        void onActionUpListener();

    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }
}
