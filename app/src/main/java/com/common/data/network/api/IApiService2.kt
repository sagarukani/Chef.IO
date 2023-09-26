package com.common.data.network.api

import com.common.data.network.model.ResponseUser
import com.common.data.network.model.request.ReqLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApiService2 {

    @POST("login")
    suspend fun login(@Body reqLogin: ReqLogin): Response<ResponseUser>

    @GET("dummy")
    suspend fun dummy(): Response<Any>
}