package com.common.data.network.repository

import com.common.data.ApiError
import com.common.data.ApiEvent
import com.common.data.ApiSuccess
import com.common.data.network.api.IApiService1
import com.common.data.network.api.IApiService2
import com.common.data.network.model.ResponseCode
import com.common.data.network.model.request.AddressReqModel
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.model.request.ScheduleReqModel
import com.common.data.network.model.request.SignupReqModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Pintu Singh on 10-02-2022
 */

class ApiRepository(
    private val apiService1: IApiService1,
    private val apiService2: IApiService2,
) {

    suspend fun login(reqLogin: LoginRequestModel) = callApi { apiService1.login(reqLogin) }
    suspend fun signup(reqLogin: SignupReqModel) = callApi { apiService1.signup(reqLogin) }
    suspend fun address(reqLogin: AddressReqModel) = callApi { apiService1.address(reqLogin) }
    suspend fun editProfile(reqLogin: ArrayList<EditProfileReqModelItem>) =
        callApi { apiService1.editProfile(reqLogin) }

    suspend fun scheduleCreate(reqLogin: ArrayList<ScheduleReqModel>) =
        callApi { apiService1.scheduleCreate(reqLogin) }

    suspend fun getownschedule() =
        callApi { apiService1.getownschedule() }

    suspend fun dummy() = callApi { apiService2.dummy() }

    private suspend fun <T> callApi(apiCall: suspend () -> Response<T>): ApiEvent<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                //Listen APIs Response/Failure in @Dispatchers.Main thread
                if (response.code() == ResponseCode.OK.code)
                    withContext(Dispatchers.Main) { ApiSuccess(response.body()) }
                else
                    throw HttpException(response)
            } catch (e: Exception) {
                ApiError(e)
            }
        }
    }


}