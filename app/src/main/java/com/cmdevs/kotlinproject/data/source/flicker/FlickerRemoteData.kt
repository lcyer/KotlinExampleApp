package com.cmdevs.kotlinproject.data.source.flicker

import com.cmdevs.kotlinproject.network.FlickerServiceInterface
import com.cmdevs.kotlinproject.network.createRetrofit

class FlickerRemoteData : FlickerDataSource{

    companion object {
        const val FLICKER_URL = "https://api.flickr.com/services/rest/"
    }

    private val flickerServiceInterface = createRetrofit(FlickerServiceInterface::class.java, FLICKER_URL)

    override fun getResentPhoto(keyword: String, page: Int, perPage: Int) = flickerServiceInterface.getFlickerResentPhotos(
        keyword = keyword,
        page = page,
        perPage = perPage)

    override fun getPhotoDetail(photoId: String) = flickerServiceInterface.getPhotoDetail(photoId)

}