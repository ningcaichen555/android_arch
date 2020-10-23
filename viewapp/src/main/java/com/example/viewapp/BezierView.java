package com.example.viewapp;

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

import androidx.annotation.Nullable;

class BezierView extends View {
    private Paint mPaint;
    private PointF start, end, control;
    private float centerX, centerY;
    private GestureDetector gestureDetector;

    private PointF start2, end2, controlL, controlR;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);

        start2 = new PointF(0, 0);
        end2 = new PointF(0, 0);
        controlL = new PointF(0, 0);
        controlR = new PointF(0, 0);

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
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
                float x = e2.getX();
                float y = e2.getY();
                controlL.x = x;
                controlL.y = y;
                invalidate();
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        start.x = centerX - 200;
        start.y = centerY-200;

        end.x = centerX + 200;
        end.y = centerY-200;

        control.x = centerX;
        control.y = centerY - 200-200;

        start2.x = centerX - 300f;
        start2.y = centerY + 300f;

        end2.x = centerX + 300f;
        end2.y = centerY + 300f;

        controlL.x = centerX - 100f;
        controlL.y = centerY + 100f;

        controlR.x = centerX + 100f;
        controlR.y = centerY + 100f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(40);

        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);

//        //绘制贝塞尔曲线
        mPaint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, mPaint);

        canvas.drawPoint(start2.x, start2.y, mPaint);
        canvas.drawPoint(end2.x, end2.y, mPaint);
        canvas.drawPoint(controlL.x, controlL.y, mPaint);
        canvas.drawPoint(controlR.x, controlR.y, mPaint);

        //绘制贝塞尔曲线 两个控制点
        Path path3 = new Path();
        path3.moveTo(start2.x, start2.y);
        path3.cubicTo(controlL.x, controlL.y,controlR.x, controlR.y, end2.x, end2.y);
        canvas.drawPath(path3, mPaint);
    }
}
