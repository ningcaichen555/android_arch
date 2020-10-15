package com.example.viewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import utils.ScreenUtil;

public class CanvasView extends View {
    private ScreenUtil screenUtil;
    private Paint pointPaint;
    private Paint linePaint;
    private RectF ovalRectF;
    //保存canvas的绘制过程
    private Picture mPicture;

    private RectF arcRect;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        screenUtil = ScreenUtil.getInstance(getContext());
        screenUtil.init(getContext());

        pointPaint = new Paint();
        pointPaint.setColor(Color.RED);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setStrokeWidth(screenUtil.dip2px(15));

        linePaint = new Paint();
        linePaint.setColor(Color.GREEN);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(screenUtil.dip2px(5f));

        ovalRectF = new RectF();

        mPicture = new Picture();

        arcRect = new RectF(screenUtil.dip2px(60f), screenUtil.dip2px(250f), screenUtil.dip2px(90f), screenUtil.dip2px(280f));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        ovalRectF.left = screenUtil.dip2px(30f);
        ovalRectF.top = screenUtil.dip2px(150f);
        ovalRectF.right = screenUtil.dip2px(60f);
        ovalRectF.bottom = screenUtil.dip2px(170f);


        //绘制点
        canvas.drawPoint(screenUtil.dip2px(30f), screenUtil.dip2px(30f), pointPaint);

        //绘制多个点
        canvas.drawPoints(new float[]{screenUtil.dip2px(30f), screenUtil.dip2px(60f), screenUtil.dip2px(30f), screenUtil.dip2px(90f)}, pointPaint);

        //绘制线
        canvas.drawLine(screenUtil.dip2px(30f), screenUtil.dip2px(120f), screenUtil.dip2px(100f), screenUtil.dip2px(120f), linePaint);

        //绘制圆
        canvas.drawCircle(screenUtil.dip2px(60f), screenUtil.dip2px(220f), screenUtil.dip2px(30f), linePaint);

        //绘制bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ios);
//        canvas.drawBitmap(bitmap, screenUtil.dip2px(30f), screenUtil.dip2px(260f), linePaint);
        canvas.drawBitmap(bitmap, null, ovalRectF, linePaint);

        //绘制椭圆
        canvas.drawOval(ovalRectF, linePaint);

        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, linePaint);


        //画布平移 平移只影响后续操作
        canvas.translate(screenUtil.dip2px(30f), screenUtil.dip2px(30f));
        canvas.drawCircle(screenUtil.dip2px(60f), screenUtil.dip2px(220f), screenUtil.dip2px(30f), linePaint);
        canvas.translate(screenUtil.dip2px(30f), screenUtil.dip2px(30f));
        canvas.drawCircle(screenUtil.dip2px(60f), screenUtil.dip2px(220f), screenUtil.dip2px(30f), linePaint);

        mPicture.beginRecording(canvas.getWidth(), canvas.getHeight());

        canvas.drawArc(arcRect, 0f, 90, true, linePaint);

        canvas.rotate(90);

        canvas.drawArc(arcRect, 0f, 90, true, linePaint);

        canvas.rotate(90);

        canvas.drawArc(arcRect, 0f, 90, true, linePaint);

        canvas.rotate(90);

        canvas.drawArc(arcRect, 0f, 90, true, linePaint);

        canvas.rotate(90);

        mPicture.endRecording();

//        mPicture.draw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
