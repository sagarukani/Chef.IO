package com.chefio.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityLoginBinding
import com.chefio.ui.forgotPassword.ForgotPasswordActivity
import com.chefio.ui.home.HomeActivity
import com.chefio.ui.main.MainActivity
import com.chefio.ui.register.RegisterActivity
import com.common.base.BaseActivity
import com.common.utils.getCustomObjects

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    AdapterView.OnItemSelectedListener {

    private val viewModel: LoginActivityViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onClick()
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener { _ ->
            if (validateInput()) {
                pref.isLoggedIn = true
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else{
                showMessage("Please enter all details and select role to login")
            }
        }
        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
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