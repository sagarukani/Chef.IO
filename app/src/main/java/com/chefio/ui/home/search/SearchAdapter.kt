package com.chefio.ui.home.search

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemSearchChefBinding
import com.common.base.BaseAdapter

class SearchAdapter : BaseAdapter<ListItemSearchChefBinding,String>(R.layout.list_item_search_chef) {
    override fun setClickableView(binding: ListItemSearchChefBinding): List<View?> {
        return emptyList()
    }

    override fun onBind(
        binding: ListItemSearchChefBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {

    }
}