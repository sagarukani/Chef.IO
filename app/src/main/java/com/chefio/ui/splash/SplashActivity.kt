package com.chefio.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chefio.R
import com.chefio.databinding.ActivitySplashBinding
import com.chefio.ui.main.MainActivity
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
        mRunnable = Runnable {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}