package com.chefio.ui.home.chat

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemChatBinding
import com.common.base.BaseAdapter

class ChatAdapter : BaseAdapter<ListItemChatBinding,String>(R.layout.list_item_chat) {
    override fun setClickableView(binding: ListItemChatBinding): List<View?> {
        return listOf(binding.root)
    }

    override fun onBind(
        binding: ListItemChatBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}