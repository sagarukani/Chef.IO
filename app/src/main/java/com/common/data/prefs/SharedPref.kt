package com.common.data.prefs

import android.content.Context
import com.common.data.network.model.UserInfo
import com.google.gson.Gson

class SharedPref(context: Context, private val gson: Gson) : EncryptedPreferences(context) {

    private inline fun <reified T> toJson(value: T?) =
        if (value == null) null else gson.toJson(value)

    private inline fun <reified T> fromJson(value: String?) =
        if (value.isNullOrEmpty()) null else gson.fromJson(value, T::class.java)

    //<editor-fold desc="Clear App Data">
    fun clearAppUserData() {
        //TODO Clear your User Data here, when user logs out
    }
    //</editor-fold>

    var authToken: String?
        get() = getString(this::authToken.name)
        set(value) = setString(this::authToken.name, value)

    var isLoggedIn : Boolean
        get() = getBoolean(this::isLoggedIn.name,false)
        set(value) = setBoolean(this::isLoggedIn.name,value)

    var isUser : Boolean
        get() = getBoolean(this::isUser.name,false)
        set(value) = setBoolean(this::isUser.name,value)


    var userInfo: UserInfo?
        set(value) = setString(this::userInfo.name, toJson(value))
        get() = try {
            fromJson(getString(this::userInfo.name))
        } catch (e: Exception) {
            null
        }

    var mtUserId: String?
        get() = getString(this::mtUserId.name)
        set(value) = setString(this::mtUserId.name, value)

}