package com.cmdevs.kotlinproject.data.source.image

import com.cmdevs.kotlinproject.data.ImageData
import com.cmdevs.kotlinproject.uil.random

class ImageLocalData : ImageDataSource {

    override fun loadImageList(imageDataList: (List<ImageData>) -> Unit, size: Int) {
        val list = mutableListOf<ImageData>()

        for(index in 1..size){
            val ranNumber = (1..10).random()+1
            val name = String.format("sample_%02d", ranNumber)

            list.add(index-1, ImageData("", name))
        }

        imageDataList(list)
    }

}