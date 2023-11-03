package com.chefio.ui.detailedRegistration.cusines

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivitySelectCusinesBinding
import com.common.base.BaseActivity
import com.common.utils.famousCuisines

class SelectCuisinesActivity :
    BaseActivity<ActivitySelectCusinesBinding>(R.layout.activity_select_cusines) {

    private lateinit var cuisineAdapter: CuisineAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {
        binding.btnSelect.setOnClickListener {
            val intent = Intent()
            intent.putExtra("list",cuisineAdapter.getAllItems().filter { it.isSelected }.map { it.name }.toList().joinToString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    private fun initView() {
        cuisineAdapter = CuisineAdapter()
        binding.rlCuisine.adapter = cuisineAdapter


        cuisineAdapter.addAll(famousCuisines)

        cuisineAdapter.setItemClickListener { view, i, cuisineModel ->
            if (view.id == R.id.llItemView) {
                cuisineModel.isSelected = !cuisineModel.isSelected
                cuisineAdapter.notifyItemChanged(i)
            }
        }
    }

}