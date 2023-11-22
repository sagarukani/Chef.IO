package com.chefio.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityRegisterBinding
import com.chefio.ui.otpVerification.VerifyOTPActivity
import com.common.base.BaseActivity
import com.common.data.network.model.request.SignupReqModel
import com.common.utils.getCustomObjects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@DelicateCoroutinesApi
@ObsoleteCoroutinesApi
@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register),
    AdapterView.OnItemSelectedListener {

    private val viewModel: ResgisterViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        onClick()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.signup.observe(this) {
            startActivity(
                Intent(
                    this,
                    VerifyOTPActivity::class.java
                ).putExtra(VerifyOTPActivity.NEW_REGISTRATION, true)
                    .putExtra(VerifyOTPActivity.PASSWORD, binding.etPassword.text.toString().trim())
                    .putExtra(VerifyOTPActivity.USERNAME, binding.etUserName.text.toString().trim())
            )
        }
    }

    private fun validateInputs(): Boolean {
        if (binding.spRole.selectedItemPosition == 0) {
            return false
        } else if (binding.etEmail.text.isNullOrEmpty()) {
            return false
        } else if (binding.etUserName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etPassword.text.isNullOrEmpty()) {
            return false
        } else if (binding.etConfirmPassword.text.isNullOrEmpty()) {
            return false
        } else if (binding.etPassword.text.equals(binding.etConfirmPassword.text)) {
            return true
        } else {
            return true
        }
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            if (validateInputs()) {
                val list = ArrayList<String>()
                list.add(if (pref.isUser) "user" else "chef")
                val req = SignupReqModel(
                    password = binding.etPassword.text.toString().trim(),
                    username = binding.etUserName.text.toString().trim(),
                    email = binding.etEmail.text.toString().trim(),
                    roles = list
                )
                viewModel.signup(req)
            } else {
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