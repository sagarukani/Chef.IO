package com.chefio.ui.orderHistory

import android.content.Intent
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivityOrderHistoryBinding
import com.chefio.ui.feedback.FeedbackActivity
import com.chefio.ui.orderDetails.OrderDetailsActivity
import com.common.base.BaseActivity

class OrderHistoryActivity :
    BaseActivity<ActivityOrderHistoryBinding>(R.layout.activity_order_history) {

    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        orderHistoryAdapter = OrderHistoryAdapter()
        binding.rlOrderHistory.adapter = orderHistoryAdapter

        orderHistoryAdapter.addItem("wefgj")

        orderHistoryAdapter.setItemClickListener { view, i, s ->
            if (view.id == R.id.btnFeedback) {
                val intent = Intent(this, FeedbackActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, OrderDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}