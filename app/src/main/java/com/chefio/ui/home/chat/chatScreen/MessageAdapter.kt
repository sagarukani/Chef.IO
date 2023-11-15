package com.chefio.ui.home.chat.chatScreen

import android.view.View
import androidx.databinding.ViewDataBinding
import com.chefio.R
import com.common.base.BaseAdapter

class MessageAdapter : BaseAdapter<ViewDataBinding,String>(R.layout.list_item_chat_sender),BaseAdapter.ILayoutSelector {

    init {
        setLayoutSelector(this)
    }
    override fun selectLayout(viewType: Int): Int {
        return when (viewType) {
            1 -> R.layout.list_item_chat_sender
            else -> R.layout.list_item_chat_receiver
        }
    }

    override fun setClickableView(binding: ViewDataBinding): List<View?> {
        return emptyList()
    }

    override fun onBind(
        binding: ViewDataBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }

    override fun getItemViewType(position: Int): Int = 1
}