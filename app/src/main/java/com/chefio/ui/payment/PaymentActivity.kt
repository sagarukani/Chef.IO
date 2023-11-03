package com.chefio.ui.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityPaymentBinding
import com.common.base.BaseActivity

class PaymentActivity : BaseActivity<ActivityPaymentBinding>(R.layout.activity_payment) {

    private lateinit var paymentAdapter : PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        paymentAdapter = PaymentAdapter()
        binding.rlCards.adapter = paymentAdapter

        paymentAdapter.addItem("hbjh")
        paymentAdapter.addItem("hbjh")
        paymentAdapter.addItem("hbjh")
    }
}