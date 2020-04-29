package com.able.imagelist.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.able.imagelist.R
import com.able.imagelist.data.api.ApiHelper
import com.able.imagelist.data.api.RetrofitBuilder
import com.able.imagelist.ui.base.ViewModelFactory
import com.able.imagelist.ui.main.viewmodel.GROUP_NUMBER
import com.able.imagelist.ui.main.viewmodel.MainViewModel
import com.able.imagelist.util.Status
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

//    private val mViewModel = lazy {
//        ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
//        ).get(MainViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        setupObservers()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return ImageListPageFragment.create(position)
            }

            override fun getItemCount(): Int {
                return GROUP_NUMBER
            }
        }
        viewPager.offscreenPageLimit = 3
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "$position"
        }.attach()


    }

//    private fun setupObservers() {
//        mViewModel.value.images.observe(this, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        AlertDialog.Builder(this).setMessage(it.toString()).show()
//                    }
//                    Status.ERROR -> {
//                        AlertDialog.Builder(this).setMessage(it.toString()).show()
//                    }
//                    Status.LOADING -> {
//                        AlertDialog.Builder(this).setMessage(it.toString()).show()
//                    }
//                }
//            }
//        })
//    }

}
