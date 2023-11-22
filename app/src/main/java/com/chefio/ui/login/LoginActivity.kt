package com.chefio.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityLoginBinding
import com.chefio.ui.forgotPassword.ForgotPasswordActivity
import com.chefio.ui.home.HomeActivity
import com.chefio.ui.register.RegisterActivity
import com.common.base.BaseActivity
import com.common.data.network.model.request.LoginRequestModel
import com.common.utils.getCustomObjects
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    AdapterView.OnItemSelectedListener {

    private val viewModel: LoginActivityViewModel by viewModels()

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

        viewModel.login.observe(this) {
            pref.isLoggedIn = true
            pref.authToken = it.accessToken
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener { _ ->
            if (validateInput()) {
                val req =
                    LoginRequestModel(
                        binding.etPassword.text.toString(),
                        binding.etEmail.text.toString()
                    )
                Timber.d(req.toString())
                viewModel.login(req)
            } else {
                showMessage("Please enter all details and select role to login")
            }
        }
        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInput(): Boolean {
        return if (binding.etEmail.text.isNullOrEmpty()) {
            false
        } else if (binding.etPassword.text.isNullOrEmpty()) {
            false
        } else binding.spRole.selectedItemPosition != 0
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