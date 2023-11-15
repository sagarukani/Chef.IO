package com.chefio.ui.home.chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chefio.R
import com.chefio.databinding.FragmentChatBinding
import com.chefio.ui.home.chat.chatScreen.ChatActivity
import com.common.base.BaseFragment

class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {

    private lateinit var chatAdapter: ChatAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        chatAdapter = ChatAdapter()
        binding.rlChefs.adapter = chatAdapter

        chatAdapter.addItem("sda")
        chatAdapter.addItem("sda")
        chatAdapter.addItem("sda")

        chatAdapter.setItemClickListener { view, i, s ->
            val intent = Intent(context, ChatActivity::class.java)
            startActivity(intent)
        }
    }

}