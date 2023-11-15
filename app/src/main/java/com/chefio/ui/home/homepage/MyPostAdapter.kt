package com.chefio.ui.home.homepage

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemMyPostBinding
import com.common.base.BaseAdapter

class MyPostAdapter : BaseAdapter<ListItemMyPostBinding, String>(R.layout.list_item_my_post) {
    override fun setClickableView(binding: ListItemMyPostBinding): List<View?> {
        return emptyList()
    }

    override fun onBind(
        binding: ListItemMyPostBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}