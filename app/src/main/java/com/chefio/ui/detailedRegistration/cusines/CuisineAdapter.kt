package com.chefio.ui.detailedRegistration.cusines

import android.view.View
import com.chefio.R
import com.chefio.databinding.ListItemCuisinesBinding
import com.common.base.BaseAdapter
import com.common.data.network.model.CuisineModel

class CuisineAdapter :
    BaseAdapter<ListItemCuisinesBinding, CuisineModel>(R.layout.list_item_cuisines) {
    override fun setClickableView(binding: ListItemCuisinesBinding): List<View?> {
        return listOf(binding.llItemView)
    }

    override fun onBind(
        binding: ListItemCuisinesBinding,
        position: Int,
        item: CuisineModel,
        payloads: MutableList<Any>?
    ) {
        binding.tvName.text = item.name
        binding.cbItem.isChecked = item.isSelected
    }
}