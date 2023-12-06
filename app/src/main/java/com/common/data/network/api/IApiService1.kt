package com.common.data.network.api

import com.common.data.network.model.AllCardResponse
import com.common.data.network.model.LoginResponse
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.ScheduleResponse
import com.common.data.network.model.UserProfile
import com.common.data.network.model.request.AddCardReqModel
import com.common.data.network.model.request.ChefProfileReqModel
import com.common.data.network.model.request.EditCardReqModel
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

    @POST("chef/profile")
    suspend fun editProfile(@Body signupReq: ArrayList<ChefProfileReqModel>): Response<Any>

    @GET("chef/profile")
    suspend fun getChefProfile(): Response<MessageResponse>

    @POST("chef/scheduleUpdate")
    suspend fun scheduleCreate(@Body schedule: ScheduleReqModel): Response<ScheduleResponse>

    @GET("chef/getownschedule")
    suspend fun getownschedule(): Response<MessageResponse>

    @GET("user/getuserprofile")
    suspend fun getUserProfile(): Response<UserProfile>

    @POST("user/addcard")
    suspend fun addCard(@Body addCardReqModel: AddCardReqModel) : Response<AllCardResponse>

    @POST("user/editcard")
    suspend fun editCard(@Body editCardReqModel: EditCardReqModel) : Response<AllCardResponse>

    @POST("user/deletecard")
    suspend fun deleteCard(@Body editCardReqModel: EditCardReqModel) : Response<MessageResponse>

    @GET("user/getcards")
    suspend fun getCards() : Response<AllCardResponse>

}
