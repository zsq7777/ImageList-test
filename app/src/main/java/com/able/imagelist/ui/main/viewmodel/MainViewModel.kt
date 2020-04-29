package com.able.imagelist.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.able.imagelist.data.repository.MainRepository
import com.able.imagelist.util.Resource

const val GROUP_NUMBER = 6

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val mPage = MutableLiveData<Int>(1)
    val mCount = MutableLiveData<Int>(5)
    fun setPage(page: Int) {
        mPage.value = page
    }

    fun setCount(count: Int) {
        mCount.value = count
    }



    val images = mPage.switchMap {page->
        liveData {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = mainRepository.getImageset(page, mCount.value!!)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }

}