package com.cmdevs.kotlinproject.view.main.home.presenter

interface HomeContract {

    interface View {
        fun hideProgress()
        fun showProgress()
        fun showLoadFail()
        fun showLoadFail(message: String)

        fun showBottomSheetDialog(photoId: String)
    }

    interface Presenter {
        //fun loadImage()
        fun loadFlickerImage()
    }

}
