package com.common.data.network.api

import com.common.data.network.model.LoginResponse
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.request.AddressReqModel
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.model.request.ScheduleReqModel
import com.common.data.network.model.request.SignupReqModel
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
    suspend fun address(@Body signupReq: AddressReqModel): Response<MessageResponse>

    @POST("chef/editchefprofile")
    suspend fun editProfile(@Body signupReq: ArrayList<EditProfileReqModelItem>): Response<MessageResponse>

    @GET("chef/profile")
    suspend fun getChefProfile(): Response<MessageResponse>

    @GET("chef/scheduleCreate")
    suspend fun scheduleCreate(@Body schedule: ArrayList<ScheduleReqModel>): Response<MessageResponse>

    @GET("chef/getownschedule")
    suspend fun getownschedule(): Response<MessageResponse>

}
