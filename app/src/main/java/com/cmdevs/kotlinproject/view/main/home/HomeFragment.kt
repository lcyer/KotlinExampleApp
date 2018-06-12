package com.cmdevs.kotlinproject.view.main.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cmdevs.kotlinproject.R
import com.cmdevs.kotlinproject.data.source.flicker.FlickerRepository
import com.cmdevs.kotlinproject.data.source.image.ImageRepository
import com.cmdevs.kotlinproject.view.main.detail.DetailImageBottomSheet
import com.cmdevs.kotlinproject.view.main.home.adapter.ImageRecyclerAdapter
import com.cmdevs.kotlinproject.view.main.home.presenter.HomeContract
import com.cmdevs.kotlinproject.view.main.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeContract.View {

    private val homePresenter : HomePresenter by lazy {
        HomePresenter(this@HomeFragment,
            FlickerRepository, imageRecyclerAdapter)
    }

    private val imageRecyclerAdapter : ImageRecyclerAdapter by lazy {
        ImageRecyclerAdapter(this@HomeFragment.context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //homePresenter.loadImage()
        homePresenter.loadFlickerImage()

        recycler_view.run {

            adapter = imageRecyclerAdapter
            layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
            addOnScrollListener(recyclerViewOnScrollListener)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler_view?.removeOnScrollListener(recyclerViewOnScrollListener)
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = recyclerView?.childCount as Int
            val totalItemCount = imageRecyclerAdapter.itemCount
            val firstVisibleItem = (recyclerView?.layoutManager as GridLayoutManager).findFirstVisibleItemPosition() ?: 0


            if(!homePresenter.isLoading && (firstVisibleItem+visibleItemCount) >= totalItemCount-5){
                //homePresenter.loadImage()
                homePresenter.loadFlickerImage()
            }

        }
    }

    override fun showBottomSheetDialog(photoId: String) {
        if(isDetached) return

        DetailImageBottomSheet.create(photoId).show(activity!!.supportFragmentManager, "DetailImageBottomSheet")
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showLoadFail() {
        if(isDetached) return
        Toast.makeText(context, "Load Fail", Toast.LENGTH_SHORT).show()
    }

    override fun showLoadFail(message: String) {
        if(isDetached) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

