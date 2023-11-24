package com.common.data.network.api

import com.common.data.network.model.LoginResponse
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.model.request.ScheduleReqModel
import com.common.data.network.model.request.SignupReqModel
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApiService1 {

    @POST("auth/signin")
    suspend fun login(@Body reqLogin: LoginRequestModel): Response<LoginResponse>


    @POST("auth/signup")
    suspend fun signup(@Body signupReq: SignupReqModel): Response<MessageResponse>

    @POST("user/address")
    suspend fun address(@Body multipartBody: MultipartBody): Response<MessageResponse>

    @POST("chef/editchefprofile")
    suspend fun editProfile(@Body signupReq: ArrayList<EditProfileReqModelItem>): Response<Any>

    @GET("chef/profile")
    suspend fun getChefProfile(): Response<MessageResponse>

    @POST("chef/scheduleCreate")
    suspend fun scheduleCreate(@Body schedule: ArrayList<ScheduleReqModel>): Response<MessageResponse>

    @GET("chef/getownschedule")
    suspend fun getownschedule(): Response<MessageResponse>

}
