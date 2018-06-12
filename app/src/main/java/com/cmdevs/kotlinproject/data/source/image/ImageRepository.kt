package com.cmdevs.kotlinproject.data.source.image

import com.cmdevs.kotlinproject.data.ImageData

object ImageRepository : ImageDataSource {

    private val imageLocalData : ImageLocalData by lazy {
        ImageLocalData()
    }

    private val imageRemoteData : ImageRemoteData by lazy {
        ImageRemoteData()
    }

    override fun loadImageList(imageDataList: (List<ImageData>) -> Unit, size: Int) {
        //imageLocalData.loadImageList(imageDataList, size)
        imageRemoteData.loadImageList(imageDataList, size)
    }

}