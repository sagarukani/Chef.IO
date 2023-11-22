package com.common.data.network.api

import com.common.data.network.model.LoginResponse
import com.common.data.network.model.request.LoginRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApiService2 {

    @POST("auth/signin")
    suspend fun login(@Body reqLogin: LoginRequestModel): Response<LoginResponse>

    @GET("dummy")
    suspend fun dummy(): Response<Any>
}