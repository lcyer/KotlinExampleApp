package com.cmdevs.kotlinproject.view.main.home.adapter.model

import com.cmdevs.kotlinproject.data.source.response.Photo

interface ImageRecyclerModel{

    //fun addItem(imageData: ImageData)
    fun addItem(photo: Photo)
    fun getItem(position: Int): Photo
    fun getItemCount(): Int
    fun notifyDataSetChange()

    var onClick: (Int) -> Unit

}