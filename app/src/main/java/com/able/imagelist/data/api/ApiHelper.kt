package com.able.imagelist.data.api

import com.able.imagelist.data.api.ApiService

class ApiHelper(private val apiService: ApiService) {
    //获取图片集
    suspend fun getImages(page: Int, count: Int) = apiService.getImages(page, count)
}