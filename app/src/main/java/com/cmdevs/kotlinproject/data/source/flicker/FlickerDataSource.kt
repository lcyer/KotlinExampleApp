package com.cmdevs.kotlinproject.data.source.flicker

import com.cmdevs.kotlinproject.data.source.response.PhotoInfo
import com.cmdevs.kotlinproject.data.source.response.PhotoResponse
import retrofit2.Call

interface FlickerDataSource {

    fun getResentPhoto(keyword: String, page: Int, perPage: Int): Call<PhotoResponse>
    fun getPhotoDetail(photoId: String): Call<PhotoInfo>
}