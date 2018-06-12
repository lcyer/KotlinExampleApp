package com.cmdevs.kotlinproject.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.cmdevs.kotlinproject.R
import com.cmdevs.kotlinproject.uil.replace
import com.cmdevs.kotlinproject.view.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment: HomeFragment by lazy {
        HomeFragment()
    }
    private val cameraFragment: CameraFragment by lazy {
        CameraFragment().apply {
            arguments =
                    Bundle().apply {
                        putInt(
                            CameraFragment.KEY_TITLE,
                            R.string.title_camera
                        )
                    }

        }
    }
    private val moreFragment: MoreFragment by lazy {
        MoreFragment().apply {
            arguments =
                    Bundle().apply {
                        putInt(MoreFragment.KEY_TITLE, R.string.title_more)
                    }
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replace(R.id.container, homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_camera -> {
                    replace(R.id.container, cameraFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_more -> {
                    replace(R.id.container, moreFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(R.id.container, homeFragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
