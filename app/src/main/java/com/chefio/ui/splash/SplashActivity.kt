package com.chefio.ui.splash

import android.content.Intent
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivitySplashBinding
import com.chefio.ui.home.HomeActivity
import com.chefio.ui.login.LoginActivity
import com.common.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private lateinit var mRunnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val delayTime = 2000L

        setRunnable()
        handler.postDelayed(mRunnable, delayTime)
    }

    private fun setRunnable() {

        pref.isLoggedIn = false
        mRunnable = Runnable {
            if (pref.isLoggedIn){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
    }
}