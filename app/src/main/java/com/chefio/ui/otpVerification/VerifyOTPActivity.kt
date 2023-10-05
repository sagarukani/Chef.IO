package com.chefio.ui.otpVerification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityRegisterBinding
import com.chefio.databinding.ActivityVerifyOtpactivityBinding
import com.common.base.BaseActivity

class VerifyOTPActivity : BaseActivity<ActivityVerifyOtpactivityBinding>(R.layout.activity_verify_otpactivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otpactivity)
    }
}