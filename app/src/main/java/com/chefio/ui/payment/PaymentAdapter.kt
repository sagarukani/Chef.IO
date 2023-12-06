package com.chefio.ui.payment

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemCardBinding
import com.common.base.BaseAdapter
import com.common.data.network.model.AllCardResponseItem

class PaymentAdapter :
    BaseAdapter<ListItemCardBinding, AllCardResponseItem>(R.layout.list_item_card) {
    override fun setClickableView(binding: ListItemCardBinding): List<View?> {
        return listOf(
            binding.ivCard,
            binding.ivDelete
        )
    }

    override fun onBind(
        binding: ListItemCardBinding,
        position: Int,
        item: AllCardResponseItem,
        payloads: MutableList<Any>?
    ) {
        binding.tvCardName.text = item.cardname
        binding.tvCardNumber.text = item.cardnumber

        binding.rbPrimary.isChecked = item.isprimary == 1
    }
}