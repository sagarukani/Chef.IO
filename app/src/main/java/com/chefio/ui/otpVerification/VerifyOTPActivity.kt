package com.chefio.ui.otpVerification

import android.content.Intent
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityVerifyOtpactivityBinding
import com.chefio.ui.detailedRegistration.ChefRegistrationActivity
import com.chefio.ui.detailedRegistration.UserRegistrationActivity
import com.chefio.ui.resetPassword.ResetPasswordActivity
import com.common.base.BaseActivity

class VerifyOTPActivity :
    BaseActivity<ActivityVerifyOtpactivityBinding>(R.layout.activity_verify_otpactivity) {

    companion object {
        const val NEW_REGISTRATION = "NEW_REGISTRATION"
    }

    private var otp = ""
    private var isNewReg = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
        initView()
        onClick()
    }

    private fun handleIntent() {
        if (intent.hasExtra(NEW_REGISTRATION)) {
            isNewReg = intent.getBooleanExtra(NEW_REGISTRATION, false)
        }
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            if (validateInput()) {
                if (isNewReg) {
                    if (pref.isUser) {
                        val intent = Intent(this, UserRegistrationActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, ChefRegistrationActivity::class.java)
                        startActivity(intent)
                    }
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