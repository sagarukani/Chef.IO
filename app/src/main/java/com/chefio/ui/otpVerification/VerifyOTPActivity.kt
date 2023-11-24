package com.chefio.ui.otpVerification

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityVerifyOtpactivityBinding
import com.chefio.ui.detailedRegistration.ChefRegistrationActivity
import com.chefio.ui.detailedRegistration.UserRegistrationActivity
import com.chefio.ui.login.LoginActivityViewModel
import com.chefio.ui.resetPassword.ResetPasswordActivity
import com.common.base.BaseActivity
import com.common.data.network.model.request.LoginRequestModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class VerifyOTPActivity :
    BaseActivity<ActivityVerifyOtpactivityBinding>(R.layout.activity_verify_otpactivity) {

    private val viewModel: LoginActivityViewModel by viewModels()

    companion object {
        const val NEW_REGISTRATION = "NEW_REGISTRATION"
        const val PASSWORD = "PASSWORD"
        const val USERNAME = "USERNAME"
    }

    private var otp = ""
    private var username = ""
    private var password = ""
    private var isNewReg = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
        initView()
        onClick()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.login.observe(this) {
            pref.isLoggedIn = true
            pref.authToken = it.accessToken
            if (pref.isUser) {
                val intent = Intent(this, UserRegistrationActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, ChefRegistrationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun handleIntent() {
        if (intent.hasExtra(NEW_REGISTRATION)) {
            isNewReg = intent.getBooleanExtra(NEW_REGISTRATION, false)
        }
        if (intent.hasExtra(USERNAME)) {
            username = intent.getStringExtra(USERNAME).toString()
        }
        if (intent.hasExtra(PASSWORD)) {
            password = intent.getStringExtra(PASSWORD).toString()
        }
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            if (validateInput()) {
                if (isNewReg) {
                    val req =
                        LoginRequestModel(
                            password,
                            username
                        )
                    Timber.d(req.toString())
                    viewModel.login(req)
                } else {
                    val intent = Intent(this, ResetPasswordActivity::class.java)
                    intent.putExtra(ResetPasswordActivity.IS_FORGOT_PASSWORD, true)
                    startActivity(intent)
                }
            } else {
                showMessage("Please enter OTP to reset your password")
            }
        }
    }

    private fun validateInput(): Boolean {
        return if (binding.otpView.isFilled()) {
            true
        } else otp == "1111"
    }

    private fun initView() {
        binding.otpView.setOnFinishListener { s: String ->
            otp = s
        }
    }
}