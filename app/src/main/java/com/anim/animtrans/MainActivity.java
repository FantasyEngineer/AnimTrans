package com.anim.animtrans;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button back;
    private Button start;
    private int oriX, oriy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        back = findViewById(R.id.back);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AnimatorSet set = new AnimatorSet();
//                set.playTogether(
//                        ObjectAnimator.ofFloat(dragview1, "translationX", 300),
//                        ObjectAnimator.ofFloat(dragview1, "translationY", 300)
//                );
//                set.setInterpolator(new AccelerateInterpolator());
//                set.setDuration(1000).start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AnimatorSet set = new AnimatorSet();
//                set.playTogether(
//                        ObjectAnimator.ofFloat(dragview1, "translationX", 0),
//                        ObjectAnimator.ofFloat(dragview1, "translationY", 0)
//                );
//                set.setInterpolator(new AccelerateInterpolator());
//                set.setDuration(1000).start();
            }
        });
    }

//    @Override
//    public void onActionMoveListener(float movex, float movey) {
//        Log.d("MainActivity", "移动");
//    }
//
//    @Override
//    public void onActionUpListener(float upx, float upy) {
//        trans(upx, upy);
//    }

    public void trans(float upx, float upy) {
//        Log.d("MainActivity", "dragview1.getTop():" + dragview1.getTop());
//        Log.d("MainActivity", "dragview1.getLeft():" + dragview1.getLeft());
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(dragview1, "translationX", upx),
//                ObjectAnimator.ofFloat(dragview1, "translationY", upy)
//        );
//        set.setInterpolator(new AccelerateInterpolator());
//        set.setDuration(1000).start();

//        ValueAnimator valueAnimator = ValueAnimator.ofInt(startNum, endNum);
//        valueAnimator.setInterpolator(new AccelerateInterpolator());//加速
//        //设置时间
//        valueAnimator.setDuration(during);
//        //获取变化的数据
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int num = (Integer) valueAnimator.getAnimatedValue();//获取变化的数值，可以做一系列的动画的绘制的参数
//                modelInfos.get(position).setNum(num);
//                //局部页面刷新
//                galleryAdapter.setFlushWithTag("tag" + position);
//                galleryAdapter.notifyDataSetChanged();
//            }
//        });
//        valueAnimator.start();
    }
}
