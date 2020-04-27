package com.able.imagelist.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.able.imagelist.R
import com.able.imagelist.ui.main.viewmodel.MainViewModel
import com.able.imagelist.util.Status

class MainActivity : AppCompatActivity() {

    private val mViewModel = lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
    }

    private fun setupObservers() {
        mViewModel.value.images.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        AlertDialog.Builder(this).setMessage(it.toString()).show()
                    }
                    Status.ERROR -> {
                        AlertDialog.Builder(this).setMessage(it.toString()).show()

                    }
                    Status.LOADING -> {
                        AlertDialog.Builder(this).setMessage(it.toString()).show()
                    }
                }
            }
        })
    }

}
