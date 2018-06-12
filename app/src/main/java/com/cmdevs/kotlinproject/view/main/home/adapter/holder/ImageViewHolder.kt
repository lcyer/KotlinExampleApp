package com.cmdevs.kotlinproject.view.main.home.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.kotlinproject.R
import com.cmdevs.kotlinproject.data.source.response.Photo
import kotlinx.android.synthetic.main.item_image_view.view.*

class ImageViewHolder(context: Context, parent: ViewGroup?, onClick: (Int)->Unit)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image_view, parent, false)){

    /*fun onBind(item: ImageData){
        itemView.onBind(item)
    }

    fun View.onBind(item: ImageData){
        tv_title.text = item.name ?: ""
        //img_view.setImageResource(resources.getIdentifier(item.fileName, "drawable", context.packageName))
        img_view.loadImage(item.fileName)
    }*/

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(photo: Photo){
        itemView.onBind(photo)
    }

    fun View.onBind(photo: Photo){
        tv_title.text = photo.title ?: ""
        img_view.loadImage(photo.getImageUrl())
    }

}