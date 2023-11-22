package com.chefio.ui.editSchedule

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityEditScheduleBinding
import com.common.base.BaseActivity
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

    private fun onClick() {
        binding.btnSave.setOnClickListener {

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