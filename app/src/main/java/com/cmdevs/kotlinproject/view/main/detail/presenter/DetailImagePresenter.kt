package com.cmdevs.kotlinproject.view.main.detail.presenter

import com.cmdevs.kotlinproject.data.source.flicker.FlickerRepository
import com.cmdevs.kotlinproject.data.source.response.PhotoInfo
import com.cmdevs.kotlinproject.uil.decimalFormat
import com.cmdevs.kotlinproject.uil.getDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailImagePresenter(val view: DetailImageContract.View, val repository: FlickerRepository) : DetailImageContract.Presenter{


    var webUrl: String = ""

    override fun loadDetailInfo(photoId: String) {
        repository.getPhotoDetail(photoId)
            .enqueue(object : Callback<PhotoInfo>{
                override fun onFailure(call: Call<PhotoInfo>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<PhotoInfo>?, response: Response<PhotoInfo>?) {
                    if(response?.isSuccessful == true) {
                        response.body().takeIf {
                            it?.stat == "ok"
                        }?.let {
                            it?.photo.let {
                                view.updateItem(
                                    it.getImageUrl(),
                                    it.title._content,
                                    it.description._content,
                                    it.dates.lastupdate.getDate("MM-dd-yyyy hh:mm:ss"),
                                    it.views.toString().decimalFormat(),
                                    it.comments._content.toString().decimalFormat()
                                )

                                view.updateToolbarItem(
                                    it.owner.getBuddyIcon(),
                                    it.owner.username
                                )

                                webUrl = it.urls.url.firstOrNull()?._content ?: ""
                            }
                        }
                    }
                }
            })
    }

    override fun loadFlickerWebPage() {
        if(!webUrl.isEmpty())
            view.showFlickerWebPage(webUrl)
    }

}
