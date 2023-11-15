package com.chefio.ui.home.homepage

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemPostBinding
import com.common.base.BaseAdapter

class FeedAdapter : BaseAdapter<ListItemPostBinding, String>(R.layout.list_item_post) {
    override fun setClickableView(binding: ListItemPostBinding): List<View?> {
        return emptyList()
    }

    override fun onBind(
        binding: ListItemPostBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}