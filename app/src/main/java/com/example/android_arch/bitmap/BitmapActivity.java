package com.example.android_arch.bitmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.android_arch.R;
import com.example.android_arch.databinding.ActivityBitmapBinding;

import java.io.File;

public class BitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBitmapBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_bitmap);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int densityDpi = displayMetrics.densityDpi;
        Log.d("imageTest-densityDpi",String.valueOf(densityDpi));

        File file = getFileStreamPath("bitmap_test.png");

//        Bitmap compress = compress();
        Bitmap compress = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap_test);
//        Bitmap compress = BitmapFactory.decodeFile(file.getAbsolutePath());
        Log.d("imageTest-filepath",file.getAbsolutePath());
        viewDataBinding.bitmapTest.setImageBitmap(compress);

        int size = sizeOf(compress);
        Log.d("imageTest-size",String.valueOf(size));
    }

    private Bitmap compress() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap_test, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        String imageType = options.outMimeType;
        Log.d("imageTest", outHeight + "    " + outWidth + "   " + imageType);
        options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap_test, options);
        Log.d("imageTest", options.outHeight + "    " + options.outWidth + "   " + options.outMimeType);
        return bitmap;
    }

    private int sizeOf(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}