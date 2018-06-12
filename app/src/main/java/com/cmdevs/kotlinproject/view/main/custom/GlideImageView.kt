package com.cmdevs.kotlinproject.view.main.custom

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cmdevs.kotlinproject.R

class GlideImageView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : AppCompatImageView(context, attrs, defStyleAttr){

    fun loadImage(url: String?, @DrawableRes loadingImages: Int = R.drawable.ic_bubble_chart_white_50dp){
        Glide.with(this)
            .load(url)
            .apply(RequestOptions().placeholder(loadingImages).centerCrop())
            .into(this)
    }

}