package com.common.data.network

import com.chefio.App
import com.common.data.network.api.IBaseService
import okhttp3.Interceptor
import okhttp3.Response


/**
 * created by Nikul on 10/2/21
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val pref = App.getInstance().getPref()
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .method(original.method, original.body)
            .header(IBaseService.Accept, "application/json")
//            .header(IBaseService.DeviceType, "A")
        requestBuilder.header(IBaseService.Authorization, "${pref.authToken}")
        val response = chain.proceed(requestBuilder.build())

//        var isTokenExpired = false
//        if (response.code == 401) {
//            val jsonString = response.body?.string()
//            val jsonObject = JSONObject(jsonString.orEmpty())
//            val isExpired = jsonObject.optBoolean("isExpired", false)
//            if (isExpired)
////                isTokenExpired = true
//        }

//        if (isTokenExpired) {
//            val client = OkHttpClient()
//            val request: Request = Request.Builder()
//                .url(BuildConfig.BaseUrl + "refreshToken")
//                .addHeader(IBaseService.Authorization, "Bearer ${pref.authToken}")
//                .addHeader(IBaseService.IS_REFRESH_TOKEN, "true")
//                .build()
//            try {
//                val refreshResponse = client.newCall(request).execute()
//                if (refreshResponse.code == 200) {
//                    val jsonData = refreshResponse.body?.string()
//                    val gson = Gson()
//                    val responseRefreshToken =
//                        gson.fromJson(jsonData, ResponseRefreshToken::class.java)
//
//                    pref.authToken = responseRefreshToken.token
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//            val modifiedRequest = original.newBuilder()
//                .addHeader(IBaseService.Authorization, "Bearer ${pref.authToken}")
//                .build()
//            return chain.proceed(modifiedRequest)
//        }
        return response
    }
}