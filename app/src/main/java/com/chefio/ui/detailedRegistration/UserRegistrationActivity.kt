package com.chefio.ui.detailedRegistration

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityUserRegistrationBinding
import com.chefio.ui.detailedRegistration.cusines.SelectCuisinesActivity
import com.chefio.ui.register.ResgisterViewModel
import com.chefio.ui.thankyou.ThankYouActivity
import com.common.base.BaseActivity
import com.common.data.network.model.request.AddressReqModel
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.utils.getGenders
import timber.log.Timber
import java.util.Calendar

class UserRegistrationActivity :
    BaseActivity<ActivityUserRegistrationBinding>(R.layout.activity_user_registration),
    AdapterView.OnItemSelectedListener {

    private val viewModel: ResgisterViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var datePicker: DatePicker


    private var cuisine: String = ""

    private var gender: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onClick()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.address.observe(this) {
            val intent = Intent(this, ThankYouActivity::class.java)
            intent.putExtra(ThankYouActivity.IS_NEW_REG, true)
            startActivity(intent)
        }
        viewModel.editProfile.observe(this) {
            val addressReqModel = AddressReqModel(
                street1 = binding.etLineOne.text.toString().trim(),
                street2 = binding.etLineTwo.text.toString().trim(),
                city = binding.etCity.text.toString().trim(),
                province = binding.etProvince.text.toString().trim(),
                postalcode = binding.etZipCode.text.toString().trim(),
                country = binding.etCountry.text.toString().trim(),
                gender = gender,
                mobilenumber = binding.etMobileNumber.text.toString().trim().toInt(),
                profilepicture = "djhcb"
            )
            viewModel.address(addressReqModel)
        }
    }

    private fun validateInput(): Boolean {
        if (binding.etFirstName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etLastName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etLastName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etBirthDate.text.isNullOrEmpty()) {
            return false
        } else if (binding.etLineOne.text.isNullOrEmpty() || binding.etLineTwo.text.isNullOrEmpty()) {
            return false
        } else if (binding.etZipCode.text.isNullOrEmpty()) {
            return false
        } else if (binding.etCity.text.isNullOrEmpty()) {
            return false
        } else if (binding.etProvince.text.isNullOrEmpty()) {
            return false
        } else if (binding.etCountry.text.isNullOrEmpty()) {
            return false
        } else if (cuisine.isNullOrEmpty()) {
            return false
        } else if (gender == 0) {
            return false
        } else {
            return true
        }
    }

    private fun onClick() {
        binding.btnRegister.setOnClickListener {
            if (validateInput()) {
                val editProfileReqModel = EditProfileReqModelItem(
                    Xlink = binding.etXLink.text.toString().trim(),
                    instagramlink = binding.etInstaLink.text.toString().trim(),
                    youtubelink = binding.etYtLink.text.toString().trim(),
                    facebooklink = binding.etFbLink.text.toString().trim(),
                    intro = binding.etCaption.text.toString().trim(),
                    cuisines = cuisine,
                    preferedcities = binding.etCity.text.toString().trim()
                )

                val model = ArrayList<EditProfileReqModelItem>()
                model.add(editProfileReqModel)

                viewModel.editProfile(model)
            }
        }

        binding.etBirthDate.setOnClickListener {
            showDatePicker()
        }

        binding.spSelectCuisines.setOnClickListener {
            val intent = Intent(this, SelectCuisinesActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun showDatePicker() {
        val year = datePicker.year
        val month = datePicker.month
        val day = datePicker.dayOfMonth

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date
                binding.etBirthDate.text = buildString {
                    append(selectedYear)
                    append("-")
                    append(selectedMonth + 1)
                    append("-")
                    append(selectedDay)
                }
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                Timber.d("data is ${data?.getStringExtra("list")}")

                cuisine = data?.getStringExtra("list").toString()
            }
        }

    private fun initView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, getGenders())

        binding.spRole.adapter = adapter

        binding.spRole.onItemSelectedListener = this

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        datePicker = DatePicker(this)

        // Set the DatePicker to the current date
        datePicker.init(currentYear, currentMonth, currentDay, null)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}