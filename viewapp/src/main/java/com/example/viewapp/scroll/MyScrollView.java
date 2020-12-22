package com.example.viewapp.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

class MyScrollView extends RelativeLayout {
    private GestureDetector gestureDetector;
    int lastX;
    int lastY;
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, (int) distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 记录触摸点坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //当按下的时候执行
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //当移动的时候执行
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left、top、right、bottom的基础上加上偏移量来控制View的位置
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                break;
            case MotionEvent.ACTION_UP:
                //当抬起的时候执行
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
