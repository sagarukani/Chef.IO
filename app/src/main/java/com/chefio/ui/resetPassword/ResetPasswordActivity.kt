package com.chefio.ui.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.chefio.R
import com.chefio.databinding.ActivityResetPasswordBinding
import com.chefio.ui.home.HomeActivity
import com.chefio.ui.login.LoginActivity
import com.common.base.BaseActivity

class ResetPasswordActivity : BaseActivity<ActivityResetPasswordBinding>(R.layout.activity_reset_password) {
    companion object{
        const val IS_FORGOT_PASSWORD = "IS_FORGOT_PASSWORD"
    }

    private var isForgotPassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
        initView()
        onClick()
    }

    private fun onClick() {
        binding.btnReset.setOnClickListener {
            if (isForgotPassword){
                if (validPassword()){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }else{
                    showMessage("Please enter correct password")
                }

            }else{

            }
        }
    }

    private fun validPassword(): Boolean {
        return if (binding.etNewPassword.text.isNullOrEmpty()){
            false
        }else !binding.etConfirmPassword.text.isNullOrEmpty()
    }

    private fun initView() {
        binding.etOldPassword.isVisible = !isForgotPassword
    }

    private fun handleIntent() {
        if (intent.hasExtra(IS_FORGOT_PASSWORD)){
            isForgotPassword = intent.getBooleanExtra(IS_FORGOT_PASSWORD,false)
        }
    }
}