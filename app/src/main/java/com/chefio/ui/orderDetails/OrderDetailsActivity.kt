package com.chefio.ui.orderDetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityOrderDetailsBinding
import com.chefio.ui.feedback.FeedbackActivity
import com.chefio.ui.resetPassword.ResetPasswordActivity
import com.common.base.BaseActivity

class OrderDetailsActivity : BaseActivity<ActivityOrderDetailsBinding>(R.layout.activity_order_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {
        binding.btnFeedback.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {

    }
}