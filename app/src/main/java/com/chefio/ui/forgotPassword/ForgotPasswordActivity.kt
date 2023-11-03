package com.chefio.ui.forgotPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.chefio.R
import com.chefio.databinding.ActivityForgotPasswordBinding
import com.chefio.ui.home.HomeActivity
import com.chefio.ui.otpVerification.VerifyOTPActivity
import com.common.base.BaseActivity
import com.common.utils.getCustomObjects

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>(R.layout.activity_forgot_password),
    AdapterView.OnItemSelectedListener {

    private lateinit var adapter: ArrayAdapter<String>

    private var isEmailSelected = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onClick()
    }

    private fun validateInput(): Boolean {
        return if (binding.etEmail.text.isNullOrEmpty()) {
            false
        } else binding.spRole.selectedItemPosition != 0
    }

    private fun onClick() {
        binding.btnSend.setOnClickListener {
            if (validateInput()){
                startActivity(Intent(this, VerifyOTPActivity::class.java))
            }else{
                showMessage("Please enter email and select role")
            }
        }
        binding.tvMobileNumber.setOnClickListener {
            isEmailSelected = !isEmailSelected
            binding.tvMobileNumber.text = if(isEmailSelected) getString(R.string.try_with_the_mobile_number) else getString(R.string.try_with_email)
            binding.etEmail.hint = if(isEmailSelected) getString(R.string.enter_your_email_address) else getString(R.string.enter_mobile_number)
            binding.etEmail.setText("")
        }
    }

    private fun initView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, getCustomObjects())

        binding.spRole.adapter = adapter

        binding.spRole.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        pref.isUser = position == 1
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}