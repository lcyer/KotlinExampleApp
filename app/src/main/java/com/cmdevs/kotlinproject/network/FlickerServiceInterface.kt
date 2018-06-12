package com.cmdevs.kotlinproject.network

import com.cmdevs.kotlinproject.BuildConfig
import com.cmdevs.kotlinproject.data.source.response.PhotoInfo
import com.cmdevs.kotlinproject.data.source.response.PhotoResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface FlickerServiceInterface{

    @POST("?method=flickr.photos.search&api_key=${BuildConfig.FLICKER_API_KEY}&format=json&nojsoncallback=1")
    fun getFlickerResentPhotos(
        @Query("text") keyword: String,
        @Query("safe_search") safeSerach: Int = 1,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Call<PhotoResponse>

    @POST("?method=flickr.photos.getInfo&api_key=${BuildConfig.FLICKER_API_KEY}&format=json&nojsoncallback=1")
    fun getPhotoDetail(
        @Query("photo_id") photoId: String
    ) : Call<PhotoInfo>


}
