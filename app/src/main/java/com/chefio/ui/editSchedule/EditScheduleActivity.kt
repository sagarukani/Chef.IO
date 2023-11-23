package com.chefio.ui.editSchedule

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityEditScheduleBinding
import com.common.base.BaseActivity
import com.common.data.network.model.request.ScheduleReqModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar

class EditScheduleActivity :
    BaseActivity<ActivityEditScheduleBinding>(R.layout.activity_edit_schedule) {

    private val viewModel: ScheduleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onClick()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.schedule.observe(this) {
            onBackPressedDispatcher.onBackPressed()

        }
    }

    private fun validateInput(): Boolean {
        if (binding.tvMondayStart.text.isBlank() && binding.tvMondayEnd.text.isBlank()) {
            return false
        } else if (binding.tvTuesdayStart.text.isBlank() && binding.tvTuesdayEnd.text.isBlank()) {
            return false
        } else if (binding.tvWednesdayStart.text.isBlank() && binding.tvWednesdayEnd.text.isBlank()) {
            return false
        } else if (binding.tvThursdayStart.text.isBlank() && binding.tvThursdayEnd.text.isBlank()) {
            return false
        } else if (binding.tvFridayStart.text.isBlank() && binding.tvFridayEnd.text.isBlank()) {
            return false
        } else if (binding.tvSaturdayStart.text.isBlank() && binding.tvSaturdayEnd.text.isBlank()) {
            return false
        } else if (binding.tvSundayStart.text.isBlank() && binding.tvSundayEnd.text.isBlank()) {
            return false
        } else {
            return true
        }
    }

    private fun onClick() {
        binding.btnSave.setOnClickListener {
            if (validateInput()) {
                val scheduleViewModel = ScheduleReqModel(
                    mondaytime = binding.tvMondayStart.text.toString()
                        .trim() + "-" + binding.tvMondayEnd.text.toString().trim(),
                    tuesdaytime = binding.tvTuesdayStart.text.toString()
                        .trim() + "-" + binding.tvTuesdayEnd.text.toString().trim(),
                    wednesdaytime = binding.tvWednesdayStart.text.toString()
                        .trim() + "-" + binding.tvWednesdayEnd.text.toString().trim(),
                    thursdaytime = binding.tvThursdayStart.text.toString()
                        .trim() + "-" + binding.tvThursdayEnd.text.toString().trim(),
                    fridaytime = binding.tvFridayStart.text.toString()
                        .trim() + "-" + binding.tvFridayEnd.text.toString().trim(),
                    saturdaytime = binding.tvSaturdayStart.text.toString()
                        .trim() + "-" + binding.tvSaturdayEnd.text.toString().trim(),
                    sundayatime = binding.tvSundayStart.text.toString()
                        .trim() + "-" + binding.tvSundayEnd.text.toString().trim()
                )

                Timber.d(scheduleViewModel.toString())
                val list = ArrayList<ScheduleReqModel>()
                list.add(scheduleViewModel)
                pref.schedule = scheduleViewModel
                viewModel.schedule(list)
            }
        }
        binding.llMondayStart.setOnClickListener {
            showTimePicker {
                binding.tvMondayStart.text = it
            }
        }
        binding.llMondayEnd.setOnClickListener {
            showTimePicker {
                binding.tvMondayEnd.text = it
            }
        }
        binding.llTuesdayStart.setOnClickListener {
            showTimePicker {
                binding.tvTuesdayStart.text = it
            }
        }
        binding.llTuesdayEnd.setOnClickListener {
            showTimePicker {
                binding.tvTuesdayEnd.text = it
            }
        }
        binding.llWednesdayStart.setOnClickListener {
            showTimePicker {
                binding.tvWednesdayStart.text = it
            }
        }
        binding.llWednesdayEnd.setOnClickListener {
            showTimePicker {
                binding.tvWednesdayEnd.text = it
            }
        }
        binding.llThursdayStart.setOnClickListener {
            showTimePicker {
                binding.tvThursdayStart.text = it
            }
        }
        binding.llThursdayEnd.setOnClickListener {
            showTimePicker {
                binding.tvThursdayEnd.text = it
            }
        }
        binding.llFridayStart.setOnClickListener {
            showTimePicker {
                binding.tvFridayStart.text = it
            }
        }
        binding.llFridayEnd.setOnClickListener {
            showTimePicker {
                binding.tvFridayEnd.text = it
            }
        }
        binding.llSaturdayStart.setOnClickListener {
            showTimePicker {
                binding.tvSaturdayStart.text = it
            }
        }
        binding.llSaturdayEnd.setOnClickListener {
            showTimePicker {
                binding.tvSaturdayEnd.text = it
            }
        }
        binding.llSundayStart.setOnClickListener {
            showTimePicker {
                binding.tvSundayStart.text = it
            }
        }
        binding.llSundayEnd.setOnClickListener {
            showTimePicker {
                binding.tvSundayEnd.text = it
            }
        }
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showTimePicker(callback: (String) -> Unit) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val selectedTime = SimpleDateFormat("hh:mm a").format(cal.time)
            callback.invoke(selectedTime)
        }

        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun initView() {

    }
}