package com.chefio.ui.home.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.chefio.R
import com.chefio.databinding.FragmentProfileBinding
import com.chefio.ui.editProfile.EditProfileActivity
import com.chefio.ui.editSchedule.EditScheduleActivity
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
        binding.llPersonalDetails.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.ivEdit.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.llPaymentPreference.setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }
        binding.llResetPassword.setOnClickListener {
            val intent = Intent(context, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        binding.llOrderHistory.setOnClickListener {
            val intent = Intent(context, OrderHistoryActivity::class.java)
            startActivity(intent)
        }
        binding.llEditSchedule.setOnClickListener {
            val intent = Intent(context, EditScheduleActivity::class.java)
            startActivity(intent)
        }
        binding.ivProfile.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            resultLauncher.launch(intent)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val selectedImageUri: Uri = result.data?.data!!
                binding.ivProfile.setImageURI(selectedImageUri)
            }
        }


    private fun initView() {
        binding.llEditSchedule.isVisible = !pref.isUser
    }

}