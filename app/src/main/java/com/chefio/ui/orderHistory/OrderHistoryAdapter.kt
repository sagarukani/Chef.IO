package com.chefio.ui.orderHistory

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemOrderHistoryBinding
import com.common.base.BaseAdapter

class OrderHistoryAdapter :
    BaseAdapter<ListItemOrderHistoryBinding, String>(R.layout.list_item_order_history) {
    override fun setClickableView(binding: ListItemOrderHistoryBinding): List<View?> {
        return listOf(binding.btnFeedback, binding.llAmountTime, binding.ivImage, binding.llDetails)
    }

    override fun onBind(
        binding: ListItemOrderHistoryBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}