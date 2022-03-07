package com.mobile.projectapi.api

import com.mobile.projectapi.models.AreaModel
import com.mobile.projectapi.models.SizeModel
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("option_size")
    suspend fun getSize(): Response<List<SizeModel>>

    @GET("option_area")
    suspend fun getArea(): Response<List<AreaModel>>
}