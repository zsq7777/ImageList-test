package com.able.imagelist.data.api

import com.able.imagelist.data.model.ImagesModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Girl/page/{page}/count/{count}")
    suspend fun getImages(@Path("page") page: Int, @Path("count") count: Int):ImagesModel
}