package com.able.imagelist.data.repository

import com.able.imagelist.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getImageset(page: Int, count: Int) = apiHelper.getImages(page, count)
}