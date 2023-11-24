package com.common.data.module

import com.chefio.BuildConfig
import com.common.data.network.api.IApiService1
import com.common.data.network.api.IApiService2
import com.common.data.network.api.IBaseService
import com.common.data.network.repository.ApiRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/**
 * Created by Pintu Singh on 10-02-2022
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideGsonConverter() = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )

    @Provides
    fun provideScalarConverter() = ScalarsConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiService1(
        gsonConverterFactory: GsonConverterFactory,
        scalarsConverterFactory: ScalarsConverterFactory,
    ): IApiService1 {
        return Retrofit.Builder().baseUrl(BuildConfig.BaseUrl)
            .client(IBaseService.getOkHttpClient(false)).addConverterFactory(gsonConverterFactory)
            .addConverterFactory(scalarsConverterFactory).build().create(IApiService1::class.java)
    }

    @Provides
    @Singleton
    fun provideApiService2(
        gsonConverterFactory: GsonConverterFactory,
        scalarsConverterFactory: ScalarsConverterFactory,
    ): IApiService2 {
        return Retrofit.Builder().baseUrl(BuildConfig.BaseUrl)
            .client(IBaseService.getOkHttpClient(false)).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            ).addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(IApiService2::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(
        apiService1: IApiService1,
        apiService2: IApiService2,
    ) = ApiRepository(apiService1, apiService2)
}