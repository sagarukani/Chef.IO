package com.chefio.ui.thankyou

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.chefio.R
import com.chefio.databinding.ActivityThankYouBinding
import com.chefio.ui.home.HomeActivity
import com.common.base.BaseActivity

class ThankYouActivity : BaseActivity<ActivityThankYouBinding>(R.layout.activity_thank_you) {

    companion object {
        const val IS_NEW_REG = "IS_NEW_REG"
    }

    private var isNewReg = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntent()
        initView()
        onClick()
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            if (isNewReg) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initView() {
        if (isNewReg) {
            binding.tvMessage.isVisible = false
        }
    }

    private fun handleIntent() {
        if (intent.hasExtra(IS_NEW_REG)) {
            isNewReg = intent.getBooleanExtra(IS_NEW_REG, false)
        }
    }
}