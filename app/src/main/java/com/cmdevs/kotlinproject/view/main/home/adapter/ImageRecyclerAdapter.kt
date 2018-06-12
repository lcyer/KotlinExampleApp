package com.cmdevs.kotlinproject.view.main.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cmdevs.kotlinproject.data.source.response.Photo
import com.cmdevs.kotlinproject.view.main.home.adapter.holder.ImageViewHolder
import com.cmdevs.kotlinproject.view.main.home.adapter.model.ImageRecyclerModel


class ImageRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ImageRecyclerModel {

//    private val list = mutableListOf<ImageData>()
    private val list = mutableListOf<Photo>()

    override lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(context, parent, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder)?.onBind(list[position])

    }

    override fun getItemCount() = list.size

    /*override fun addItem(imageData: ImageData) {
        list.add(imageData)
    }*/

    override fun addItem(photo: Photo) {
        list.add(photo)
    }

    override fun getItem(position: Int) = list.get(position)

    override fun notifyDataSetChange() {
        notifyDataSetChanged()
    }

}
