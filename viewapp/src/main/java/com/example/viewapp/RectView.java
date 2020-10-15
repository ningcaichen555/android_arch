package com.example.viewapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import utils.ScreenUtil;

public class RectView extends View {
    private Paint mPaint;
    private RectF rectF = new RectF(0, 0, 0, 0);
    private ScreenUtil instance;
    private int progress;

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public RectView(Context context) {
        this(context, null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        instance = ScreenUtil.getInstance(getContext());
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.left = instance.dip2px(15);
        rectF.top = 0;
        rectF.right = progress;
        rectF.bottom = rectF.top + instance.dip2px(40);
        canvas.drawRoundRect(rectF, instance.dip2px(15), instance.dip2px(15), mPaint);
    }
}
