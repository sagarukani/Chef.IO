package com.chefio.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.common.base.BaseActivity
import com.common.data.database.entities.UserLocal
import com.common.utils.EventBus
import com.common.utils.EventBus.listenEvent
import com.chefio.R
import com.chefio.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEventBus()
        setUpUi()
        setUpObserver()

        viewModel.callApi()


        lifecycleScope.launch {
            EventBus.publish(Intent())
            EventBus.publish(0)
        }
        // or you can use extension function
        // publishEvent(Intent())
        // publishEvent(0)
//        startActivity(Intent(this, RefreshTokenActivity::class.java))
    }

    private fun initEventBus() {
        listenEvent<Intent> {

        }
        listenEvent<Int> {

        }
    }

    private fun setUpUi() {
        binding.textMessage.text = "Activity Main Binding"
        binding.textMessage.setOnClickListener {
            viewModel.insertUser(UserLocal().apply { displayAlias = "Rohan" })
        }

    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.userInfoError.observe(this) { it.printStackTrace() }

        viewModel.userInfo.observe(this) {
            Timber.e("List of Users: ${it.size}")
        }

        viewModel.localUser?.observe(this) {
            Timber.e("Local Users: ${it.size}")
        }
    }
}