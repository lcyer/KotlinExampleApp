package com.cmdevs.kotlinproject.data.source.flicker

import com.cmdevs.kotlinproject.data.source.response.PhotoInfo
import retrofit2.Call

object FlickerRepository : FlickerDataSource{

    private val flickerRemoteData: FlickerRemoteData by lazy{
        FlickerRemoteData()
    }

    override fun getResentPhoto(keyword: String, page: Int, perPage: Int) = flickerRemoteData.getResentPhoto(keyword, page, perPage)
    override fun getPhotoDetail(photoId: String) = flickerRemoteData.getPhotoDetail(photoId)
}