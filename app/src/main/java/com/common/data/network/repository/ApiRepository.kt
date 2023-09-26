package com.common.data.network.repository

import com.common.data.ApiError
import com.common.data.ApiEvent
import com.common.data.ApiSuccess
import com.common.data.network.api.IApiService1
import com.common.data.network.api.IApiService2
import com.common.data.network.model.ResponseCode
import com.common.data.network.model.request.ReqLogin
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

    suspend fun getUsers() = callApi { apiService1.getUsers() }

    suspend fun login(reqLogin: ReqLogin) = callApi { apiService2.login(reqLogin) }

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