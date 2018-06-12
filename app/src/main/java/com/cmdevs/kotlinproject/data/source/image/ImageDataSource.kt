package com.cmdevs.kotlinproject.data.source.image

import com.cmdevs.kotlinproject.data.ImageData

interface ImageDataSource {

    fun loadImageList(imageDataList: (List<ImageData>)->Unit, size: Int)
}