package com.chefio.ui.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chefio.R
import com.chefio.databinding.FragmentHomeBinding
import com.chefio.databinding.FragmentSearchBinding
import com.chefio.ui.home.homepage.PostAdapter
import com.common.base.BaseFragment

class SearchFragment  : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        searchAdapter = SearchAdapter()
        binding.rlChefs.adapter = searchAdapter

        searchAdapter.addItem("ed")
        searchAdapter.addItem("ed")
        searchAdapter.addItem("ed")
        searchAdapter.addItem("ed")
    }
}