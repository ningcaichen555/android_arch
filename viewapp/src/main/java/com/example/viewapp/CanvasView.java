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
    private Paint arcPaint;
    private RectF ovalRectF;
    //保存canvas的绘制过程
    private Picture mPicture;

    private RectF arcRect;
    private Paint textPaint;

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

        arcPaint = new Paint();
        arcPaint.setColor(Color.YELLOW);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(screenUtil.dip2px(5f));

        ovalRectF = new RectF();

        mPicture = new Picture();

        arcRect = new RectF(screenUtil.dip2px(60f), screenUtil.dip2px(250f), screenUtil.dip2px(90f), screenUtil.dip2px(280f));

        textPaint = new Paint();
        textPaint.setColor(Color.argb(255, 22, 222, 222));
        textPaint.setTextSize(screenUtil.dip2px(40f));
        textPaint.setStyle(Paint.Style.FILL);
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
        canvas.save();
        canvas.translate(screenUtil.dip2px(30f), screenUtil.dip2px(30f));
        canvas.drawCircle(screenUtil.dip2px(60f), screenUtil.dip2px(220f), screenUtil.dip2px(30f), linePaint);
        canvas.translate(screenUtil.dip2px(30f), screenUtil.dip2px(30f));
        canvas.drawCircle(screenUtil.dip2px(60f), screenUtil.dip2px(220f), screenUtil.dip2px(30f), linePaint);
        canvas.restore();


        Canvas canvasPic = mPicture.beginRecording(canvas.getWidth(), canvas.getHeight());

        canvasPic.drawArc(arcRect, 0f, 90, true, arcPaint);

        canvasPic.drawLine(screenUtil.dip2px(30f), screenUtil.dip2px(400f), screenUtil.dip2px(100f), screenUtil.dip2px(400f), arcPaint);

        canvasPic.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvasPic.drawArc(arcRect, 0f, 90, true, arcPaint);

        canvasPic.drawLine(screenUtil.dip2px(30f), screenUtil.dip2px(400f), screenUtil.dip2px(100f), screenUtil.dip2px(400f), arcPaint);

        canvasPic.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvasPic.drawArc(arcRect, 0f, 90, true, arcPaint);

        canvasPic.drawLine(screenUtil.dip2px(30f), screenUtil.dip2px(400f), screenUtil.dip2px(100f), screenUtil.dip2px(400f), arcPaint);

        canvasPic.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvasPic.drawArc(arcRect, 0f, 90, true, arcPaint);

        canvasPic.drawLine(screenUtil.dip2px(30f), screenUtil.dip2px(400f), screenUtil.dip2px(100f), screenUtil.dip2px(400f), arcPaint);

        canvasPic.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);

        mPicture.endRecording();

        canvas.drawPicture(mPicture);

//
//        第一类 只能指定文本基线位置(基线x默认在字符串左侧，基线y默认在字符串下方)。
//        public void drawText (String text, float x, float y, Paint paint)
//        public void drawText (String text, int start, int end, float x, float y, Paint paint)
//        public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
//        public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
//
//        第二类  第二类可以分别指定每个文字的位置。
//        public void drawPosText (String text, float[] pos, Paint paint)
//        public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
//
//        第三类  第三类是指定一个路径，根据路径绘制文字。
//        public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
//        public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)

        //以画布中心为中心进行缩放
//        canvas.scale(0.5f, 0.5f, (float) canvas.getWidth() / 2, (float) canvas.getHeight() / 2);

        String text = "abcdef";
        canvas.drawText(text, 0, screenUtil.dip2px(30f), textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }
}
