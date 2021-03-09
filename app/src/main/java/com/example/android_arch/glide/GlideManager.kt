package com.example.android_arch.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android_arch.App

/**
 * @name android_arch
 * @class name：com.example.android_arch.glide
 * @class describe
 * @author caichen QQ:345233199
 * @time 2021/3/9 22:28
 * @class describe
 */
object GlideManager {
    private fun loadImage() {
        Glide.with(App.getContext()).load("").into(ImageView(App.getContext()))
    }

}