package com.chefio.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityRegisterBinding
import com.common.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}