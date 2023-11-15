package com.chefio.ui.home.chat.chatScreen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.chefio.R
import com.chefio.databinding.ActivityChatBinding
import com.chefio.databinding.DialogAttachmentBinding
import com.common.base.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {

    private lateinit var messageAdapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onClick()
    }

    private fun onClick() {
        binding.ivPlus.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val binding: DialogAttachmentBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.dialog_attachment,
                null,
                false
            )

        val dialog = BottomSheetDialog(this, R.style.DialogCustomTheme)
        dialog.window?.setBackgroundDrawable(null)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)



        dialog.setContentView(binding.root)
        dialog.show()
    }

    private fun initView() {
        messageAdapter = MessageAdapter()
        binding.rlMessages.adapter = messageAdapter

        messageAdapter.addItem("ds")
        messageAdapter.addItem("ds")
        messageAdapter.addItem("ds")
    }
}