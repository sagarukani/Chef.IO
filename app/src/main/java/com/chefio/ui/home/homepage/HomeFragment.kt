package com.chefio.ui.home.homepage

import android.os.Bundle
import android.view.View
import com.chefio.R
import com.chefio.databinding.FragmentHomeBinding
import com.common.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var postAdapter: PostAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        postAdapter = PostAdapter()
        binding.rlPost.adapter = postAdapter

        postAdapter.addItem("sda")
        postAdapter.addItem("sda")
        postAdapter.addItem("sda")
    }
}