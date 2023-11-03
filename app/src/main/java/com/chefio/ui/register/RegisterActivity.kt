package com.chefio.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.chefio.R
import com.chefio.databinding.ActivityRegisterBinding
import com.chefio.ui.otpVerification.VerifyOTPActivity
import com.common.base.BaseActivity
import com.common.utils.getCustomObjects

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register),
    AdapterView.OnItemSelectedListener {

    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        onClick()
    }

    private fun validateInputs(): Boolean {
        if (binding.spRole.selectedItemPosition == 0) {
            return false
        } else if (binding.etEmail.text.isNullOrEmpty()) {
            return false
        } else if (binding.etMobileNumber.text.isNullOrEmpty()) {
            return false
        } else if (binding.etPassword.text.isNullOrEmpty()) {
            return false
        } else if (binding.etConfirmPassword.text.isNullOrEmpty()) {
            return false
        } else if (binding.etPassword.text.equals(binding.etConfirmPassword.text)){
            return true
        }else{
            return true
        }
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            if (validateInputs()) {
                startActivity(
                    Intent(
                        this,
                        VerifyOTPActivity::class.java
                    ).putExtra(VerifyOTPActivity.NEW_REGISTRATION, true)
                )
            }else{
                showMessage("Please enter all details to register yourself")
            }
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
        TODO("Not yet implemented")
    }
}