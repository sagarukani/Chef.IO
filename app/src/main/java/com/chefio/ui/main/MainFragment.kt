package com.chefio.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.common.base.BaseFragment
import com.chefio.R
import com.chefio.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainFragmentViewModel by viewModels<MainFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textMessage.text = "Fragment Main Binding"
    }
}