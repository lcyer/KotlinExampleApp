package com.cmdevs.kotlinproject.view.main.home.presenter

import com.cmdevs.kotlinproject.data.source.response.PhotoResponse
import com.cmdevs.kotlinproject.data.source.flicker.FlickerRepository
import com.cmdevs.kotlinproject.view.main.home.adapter.model.ImageRecyclerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(val view: HomeContract.View,
                    private val flickerRepository: FlickerRepository,
                    private val imageRecyclerModel: ImageRecyclerModel) : HomeContract.Presenter{

    var isLoading = false

    private val perPage: Int = 50
    private var page: Int = 0

    init {
        imageRecyclerModel.onClick = {
            view.showBottomSheetDialog(imageRecyclerModel.getItem(it).id)
        }
    }

    override fun loadFlickerImage() {
        isLoading = true
        view.showProgress()

        flickerRepository.getResentPhoto("Eiffel Tower", ++page, perPage)
            .enqueue(object : Callback<PhotoResponse>{
                override fun onFailure(call: Call<PhotoResponse>?, t: Throwable?) {

                    view.hideProgress()
                    view.showLoadFail()

                    isLoading = false
                }

                override fun onResponse(call: Call<PhotoResponse>?, response: Response<PhotoResponse>?) {

                    if(response?.isSuccessful == true) {
                        response.body().takeIf {
                            it?.stat == "ok"
                        }?.let {
                            //성공한 경우만 아이템 추가.
                            page = it.photos.page

                            it.photos.photo.forEach{
                                imageRecyclerModel.addItem(it)
                            }

                            imageRecyclerModel.notifyDataSetChange()
                        } ?: let{
                            view.showLoadFail("code ${response.body()?.code} message ${response.body()?.message}")
                        }
                    } else {
                        view.showLoadFail()
                    }

                    view.hideProgress()
                    isLoading = false
                }
            })
    }


    /*override fun loadImage() {
        ImageAsyncTask(this, view, imageRepository, imageRecyclerModel).execute()
    }*/


    /*class ImageAsyncTask(val homePresenter: HomePresenter,
                         val view: HomeContract.View,
                         private val imageRepository: ImageRepository,
                         private val imageRecyclerModel: ImageRecyclerModel) : AsyncTask<Unit, Unit, Unit >() {

        override fun onPreExecute() {
            super.onPreExecute()

            view.showProgress()
            homePresenter.isLoading = true;
        }

        override fun doInBackground(vararg params: Unit?) {


            imageRepository.loadImageList({

                it.forEach{
                    imageRecyclerModel.addItem(it)
                }

            },10)

            Thread.sleep(1000)
        }

        override fun onPostExecute(result: Unit) {
            super.onPostExecute(result)

            view.hideProgress()
            homePresenter.isLoading = false
            imageRecyclerModel.notifyDataSetChange()
        }
    }*/

}
