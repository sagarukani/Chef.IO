package com.chefio.ui.login

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.chefio.R
import com.chefio.databinding.ActivityLoginBinding
import com.common.base.BaseActivity
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    AdapterView.OnItemSelectedListener {

    private lateinit var mySpinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val customObjects = getCustomObjects()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, customObjects)

        binding.spRole.adapter = adapter

        binding.spRole.onItemSelectedListener = this
    }

    private fun getCustomObjects(): ArrayList<String> {
        val customObjects = ArrayList<String>()
        customObjects.apply {
            add("Select role")
            add("User")
            add("Chef")
        }
        return customObjects
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Timber.d("pos" + position + id.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Timber.d("Nothing selected")
    }
}