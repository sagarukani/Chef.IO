package com.chefio

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import coil.ImageLoader
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import coil.util.CoilUtils
import com.common.data.prefs.SharedPref
import com.common.multilanguage.LocaleManager
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    private lateinit var pref: SharedPref

    companion object {
        private lateinit var mInstance: App

        @Synchronized
        fun getInstance(): App = mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        if (BuildConfig.DEBUG) Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
            }
        })

        ImageLoader.Builder(this)
            .componentRegistry {
                add(VideoFrameFileFetcher(this@App))
                add(VideoFrameUriFetcher(this@App))
            }
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this@App))
                    .build()
            }
            .build()

        pref = SharedPref(applicationContext, Gson())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(if (base != null) LocaleManager.setLocale(base) else base)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    fun getPref() = pref
}