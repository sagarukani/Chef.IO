package com.chefio.ui.home.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.chefio.BuildConfig
import com.chefio.R
import com.chefio.databinding.FragmentProfileBinding
import com.chefio.ui.editProfile.EditProfileActivity
import com.chefio.ui.editSchedule.EditScheduleActivity
import com.chefio.ui.orderHistory.OrderHistoryActivity
import com.chefio.ui.payment.PaymentActivity
import com.chefio.ui.register.ResgisterViewModel
import com.chefio.ui.resetPassword.ResetPasswordActivity
import com.chefio.ui.splash.SplashActivity
import com.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ResgisterViewModel by viewModels<ResgisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
        setUpObserver()

        Timber.d(pref.address.toString())
        Timber.d(pref.editProfileReqModel.toString())

        viewModel.getProfile()
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.userProfile.observe(this) {
            Timber.d(it.toString())
            pref.userProfile = it
            binding.llEditSchedule.isVisible = !pref.isUser
            binding.tvName.text = pref.address?.firstname.plus(it.lastname)
            binding.tvNumber.text = it?.mobilenumber.toString() ?: ""
            Glide.with(this).load((BuildConfig.PhotosUrl + it?.profilepicture) ?: "")
                .placeholder(R.drawable.image)
                .into(binding.ivProfile)
        }
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
        binding.btnLogin.setOnClickListener {
            pref.clearAppUserData()
            pref.isLoggedIn = false
            val intent = Intent(context, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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
        binding.tvName.text = pref.address?.firstname.plus(pref.address?.lastname)
        binding.tvNumber.text = pref.address?.mobilenumber.toString()
        Glide.with(this).load(pref.address?.imagePath.toString()).placeholder(R.drawable.image)
            .into(binding.ivProfile)
    }

}