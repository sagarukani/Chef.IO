package com.common.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.common.data.network.model.ResponseCode
import com.common.data.prefs.SharedPref
import com.common.multilanguage.LocaleManager
import com.common.utils.AppLoader
import com.google.android.material.snackbar.Snackbar
import com.chefio.App
import com.chefio.R
import kotlinx.coroutines.channels.ReceiveChannel
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException


abstract class BaseFragmentActivity<VB : ViewDataBinding>(private val layoutRes: Int) : FragmentActivity() {

    protected lateinit var binding: VB
    protected val listSubscription = ArrayList<ReceiveChannel<*>>()
    private val appLoader: AppLoader by lazy { AppLoader(this) }
    val handler: Handler by lazy { Handler(Looper.getMainLooper()) }
    val pref: SharedPref by lazy { App.getInstance().getPref() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(if (base != null) LocaleManager.setLocale(base) else base)
    }

    /**
     * Use this method when user manually change the language from app.
     * Usage:
     *   setNewLocale(LocaleManager.ENGLISH)
     *   setNewLocale(LocaleManager.GERMAN)
     * */
    protected fun setNewLocale(language: String) {
        LocaleManager.setNewLocale(this, language)
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        listSubscription.forEach { it.cancel() }
    }

    open fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun updateLoaderUI(isShow: Boolean) {
        if (isShow) appLoader.show() else appLoader.dismiss()
    }

    fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                handleResponseError(throwable)
            }
            is ConnectException -> {
                showMessage(getString(R.string.msg_no_internet))
            }
            is SocketTimeoutException -> {
                showMessage(getString(R.string.time_out))
            }
        }
    }

    private fun handleResponseError(throwable: HttpException) {
        when (throwable.code()) {
            ResponseCode.InvalidData.code -> {
                val errorRawData = throwable.response()?.errorBody()?.string()?.trim()
                if (!errorRawData.isNullOrEmpty()) {
                    val jsonObject = JSONObject(errorRawData)
                    val jObject = jsonObject.optJSONObject("errors")
                    if (jObject != null) {
                        val keys: Iterator<String> = jObject.keys()
                        if (keys.hasNext()) {
                            val msg = StringBuilder()
                            while (keys.hasNext()) {
                                val key: String = keys.next()
                                if (jObject.get(key) is String) {
                                    msg.append("- ${jObject.get(key)}\n")
                                }
                            }
                            errorDialog(msg.toString(), getString(R.string.alert))
                        } else {
                            errorDialog(jsonObject.optString("message", ""))
                        }
                    } else {
                        errorDialog(JSONObject(errorRawData).optString("message"), getString(R.string.alert))
                    }
                }
            }
            ResponseCode.Unauthenticated.code -> {
                val errorRawData = throwable.response()?.errorBody()?.string()?.trim()
                if (!errorRawData.isNullOrEmpty()) {
                    AlertDialog.Builder(this)
                        .setTitle(R.string.alert)
                        .setMessage(errorRawData)
                        .setPositiveButton(R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                            onAuthFail()
                        }
                        .show()
                } else {
                    onAuthFail()
                }
            }
            ResponseCode.ForceUpdate.code -> {

            }
            ResponseCode.InternalServerError.code -> errorDialog("Internal Server error")
            ResponseCode.BadRequest.code,
            ResponseCode.Unauthorized.code,
            ResponseCode.NotFound.code,
            ResponseCode.RequestTimeOut.code,
            ResponseCode.Conflict.code,
            ResponseCode.Blocked.code -> {
                val errorRawData = throwable.response()?.errorBody()?.string()?.trim()
                if (!errorRawData.isNullOrEmpty()) {
                    errorDialog(JSONObject(errorRawData).optString("message", ""))
                }
            }
        }
    }

    private fun onAuthFail() {
        pref.clearAppUserData()
        //TODO Redirect User to Login Screen
    }

    private fun errorDialog(optString: String?, title: String = getString(R.string.app_name)) {
        optString?.let {
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(it)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}

