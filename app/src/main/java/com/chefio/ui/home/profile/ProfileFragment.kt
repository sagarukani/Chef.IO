package com.chefio.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chefio.R
import com.chefio.databinding.FragmentProfileBinding
import com.chefio.ui.editProfile.EditProfileActivity
import com.chefio.ui.orderHistory.OrderHistoryActivity
import com.chefio.ui.payment.PaymentActivity
import com.chefio.ui.resetPassword.ResetPasswordActivity
import com.common.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {
        binding.llPersonalDetails.setOnClickListener{
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.llPaymentPreference.setOnClickListener{
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }
        binding.llResetPassword.setOnClickListener{
            val intent = Intent(context, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        binding.llOrderHistory.setOnClickListener{
            val intent = Intent(context, OrderHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {

    }

}