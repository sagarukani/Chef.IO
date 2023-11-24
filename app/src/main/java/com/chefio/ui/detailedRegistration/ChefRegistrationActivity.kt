package com.chefio.ui.detailedRegistration

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.chefio.R
import com.chefio.databinding.ActivityChefRegistrationBinding
import com.chefio.ui.detailedRegistration.cusines.SelectCuisinesActivity
import com.chefio.ui.register.ResgisterViewModel
import com.chefio.ui.thankyou.ThankYouActivity
import com.common.base.BaseActivity
import com.common.data.network.model.request.AddressReqModel
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.utils.getGenders
import com.common.utils.multipartImageBody
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.util.Calendar

@AndroidEntryPoint
class ChefRegistrationActivity :
    BaseActivity<ActivityChefRegistrationBinding>(R.layout.activity_chef_registration),
    AdapterView.OnItemSelectedListener {

    private val viewModel: ResgisterViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var datePicker: DatePicker


    private var cuisine: String = ""
    private var path: String = ""
    private lateinit var uri: Uri
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
        viewModel.editProfile.observe(this) {
            val intent = Intent(this, ThankYouActivity::class.java)
            intent.putExtra(ThankYouActivity.IS_NEW_REG, true)
            startActivity(intent)
        }
    }

    private fun validateInput(): Boolean {
        if (binding.etFirstName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etLastName.text.isNullOrEmpty()) {
            return false
        } else if (binding.etCaption.text.isNullOrEmpty()) {
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
        } else return gender != 0
    }


    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                handlePickedImage(uri)
            } else {
                showMessage("Please select other image")
            }
        }

    private fun handlePickedImage(uri: Uri) {
        val filePath: String? = getRealPathFromURI(this, uri)
        path = filePath.toString()
        Glide.with(this).load(filePath).into(binding.ivProfile)
    }

    private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        return cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            columnIndex.let { it1 -> it.getString(it1) }
        }
    }

    private fun onClick() {
        binding.ivProfile.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
        binding.btnRegister.setOnClickListener {
            if (validateInput() && path.isNotEmpty()) {
                val destination = File(path)

                val builder = destination.multipartImageBody("file")
                builder.addFormDataPart("street1", binding.etLineOne.text.toString().trim())
                builder.addFormDataPart("street2", binding.etLineTwo.text.toString().trim())
                builder.addFormDataPart("city", binding.etCity.text.toString().trim())
                builder.addFormDataPart("province", binding.etProvince.text.toString().trim())
                builder.addFormDataPart("postalcode", binding.etZipCode.text.toString().trim())
                builder.addFormDataPart("country", binding.etCountry.text.toString().trim())
                builder.addFormDataPart("gender", gender.toString())
                builder.addFormDataPart(
                    "mobilenumber",
                    binding.etMobileNumber.text.toString().trim()
                )
                builder.addFormDataPart("firstname", binding.etFirstName.text.toString().trim())
                builder.addFormDataPart("lastname", binding.etLastName.text.toString().trim())

                viewModel.address(builder.build())

                val addressReqModel = AddressReqModel(
                    street1 = binding.etLineOne.text.toString().trim(),
                    street2 = binding.etLineTwo.text.toString().trim(),
                    city = binding.etCity.text.toString().trim(),
                    province = binding.etProvince.text.toString().trim(),
                    postalcode = binding.etZipCode.text.toString().trim(),
                    country = binding.etCountry.text.toString().trim(),
                    gender = gender,
                    mobilenumber = binding.etMobileNumber.text.toString().trim().toInt(),
                    imagePath = path,
                    file = path,
                    firstname = binding.etFirstName.text.toString().trim(),
                    lastname = binding.etLastName.text.toString().trim()
                )

                pref.address = addressReqModel
            } else {
                showMessage("Please enter all details and choose profile picture")
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

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                Timber.d("data is ${data?.getStringExtra("list")}")

                cuisine = data?.getStringExtra("list").toString()
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

    private fun initView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, getGenders())

        binding.spRole.adapter = adapter

        binding.spRole.onItemSelectedListener = this

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        datePicker = DatePicker(this)

        datePicker.init(currentYear, currentMonth, currentDay, null)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}