package com.chefio.ui.home.homepage

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.chefio.R
import com.chefio.databinding.FragmentHomeBinding
import com.common.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var postAdapter: PostAdapter
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var myPostAdapter: MyPostAdapter
    private var indicatorWidth: Int = 2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        if (pref.isUser) {
            postAdapter = PostAdapter()
            binding.rlPost.adapter = postAdapter

            postAdapter.addItem("sda")
            postAdapter.addItem("sda")
            postAdapter.addItem("sda")
        } else {


            feedAdapter = FeedAdapter()
            myPostAdapter = MyPostAdapter()

            binding.rlFeed.adapter = feedAdapter
            binding.rlMyPost.adapter = myPostAdapter

            feedAdapter.addItem("sfc")
            feedAdapter.addItem("sfc")
            feedAdapter.addItem("sfc")

            myPostAdapter.addItem("sdff")
            myPostAdapter.addItem("sdff")
            myPostAdapter.addItem("sdff")


            binding.tlTabLayout.addTab(
                binding.tlTabLayout.newTab().setText("Feed")
            )
            binding.tlTabLayout.addTab(
                binding.tlTabLayout.newTab().setText("My Posts")
            )
            binding.tlTabLayout.tabGravity = TabLayout.GRAVITY_FILL
            binding.tlTabLayout.post {
                try {
                    indicatorWidth = binding.tlTabLayout.width / binding.tlTabLayout.tabCount
                    //Assign new width
                    val indicatorParams = binding.indicator.layoutParams as FrameLayout.LayoutParams
                    indicatorParams.width = indicatorWidth
                    binding.indicator.layoutParams = indicatorParams
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            binding.tlTabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // Smoothly move the indicator to the selected tab
                    val x = tab?.position?.times(indicatorWidth) ?: 0
                    binding.indicator.animate().translationX(x.toFloat()).setDuration(300).start()

                    if (x==0){
                        binding.rlFeed.isVisible = true
                        binding.rlMyPost.isVisible = false
                    }else{
                        binding.rlFeed.isVisible = false
                        binding.rlMyPost.isVisible = true
                    }

                }
            })
        }

    }
}