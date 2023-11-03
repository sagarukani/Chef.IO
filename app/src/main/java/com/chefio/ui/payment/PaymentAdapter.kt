package com.chefio.ui.payment

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemCardBinding
import com.common.base.BaseAdapter

class PaymentAdapter : BaseAdapter<ListItemCardBinding,String>(R.layout.list_item_card) {
    override fun setClickableView(binding: ListItemCardBinding): List<View?> {
        return emptyList()
    }

    override fun onBind(
        binding: ListItemCardBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}