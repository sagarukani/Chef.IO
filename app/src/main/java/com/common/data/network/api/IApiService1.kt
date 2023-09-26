package com.common.data.network.api

import com.common.data.network.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET

interface IApiService1 {

    @GET("api/users?page=2")
    suspend fun getUsers(): Response<List<UserInfo>>
}
