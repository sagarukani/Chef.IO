package com.chefio.ui.editProfile

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import com.chefio.R
import com.chefio.databinding.ActivityEditProfileBinding
import com.chefio.ui.detailedRegistration.cusines.SelectCuisinesActivity
import com.chefio.ui.thankyou.ThankYouActivity
import com.common.base.BaseActivity
import com.common.utils.getGenders
import timber.log.Timber
import java.util.Calendar

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile),
    AdapterView.OnItemSelectedListener {

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var datePicker: DatePicker


    private var cuisine: String = ""

    private var gender: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        onClick()

    }

    private var resultLauncherImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                Timber.d("data is ${data?.getStringExtra("list")}")

                cuisine = data?.getStringExtra("list").toString()
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
        binding.ivProfile.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            resultLauncherImage.launch(intent)
        }
        binding.btnRegister.setOnClickListener {
            if (validateInput()) {
                val intent = Intent(this, ThankYouActivity::class.java)
                intent.putExtra(ThankYouActivity.IS_NEW_REG, true)
                startActivity(intent)
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