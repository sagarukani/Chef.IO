package com.chefio.ui.payment

import android.os.Bundle
import androidx.activity.viewModels
import com.chefio.R
import com.chefio.databinding.ActivityPaymentBinding
import com.common.base.BaseActivity
import com.common.data.network.model.request.AddCardReqModel
import com.common.data.network.model.request.EditCardReqModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : BaseActivity<ActivityPaymentBinding>(R.layout.activity_payment) {

    private lateinit var paymentAdapter: PaymentAdapter

    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setUpObserver()
        onClick()

        viewModel.getAllCards()
    }

    private fun onClick() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.btnLogin.setOnClickListener {
            if (binding.etCardName.text.isNotEmpty() && binding.etCardNumber.text.isNotEmpty() && binding.etExpiry.text.isNotEmpty() && binding.etCVV.text.isNotEmpty()) {
                val addCardReqModel = AddCardReqModel(
                    binding.etCVV.text.trim().toString(),
                    binding.etExpiry.text.trim().toString(),
                    binding.etCardName.text.trim().toString(),
                    binding.etCardNumber.text.trim().toString()
                )
                viewModel.addCard(addCardReqModel)

                binding.etCVV.setText("")
                binding.etExpiry.setText("")
                binding.etCardName.setText("")
                binding.etCardNumber.setText("")
            } else {
                showMessage("Please enter all details to add new card")
            }
        }
    }

    private fun setUpObserver() {
        viewModel.apiErrors.observe(this) { handleError(it) }

        viewModel.appLoader.observe(this) { updateLoaderUI(it) }

        viewModel.deleteCard.observe(this) {
            viewModel.getAllCards()
        }
        viewModel.allCards.observe(this) {
            paymentAdapter.addAll(it)
        }
        viewModel.editCards.observe(this) {
            viewModel.getAllCards()
        }
        viewModel.cards.observe(this) {
            viewModel.getAllCards()
        }
    }

    private fun initView() {
        paymentAdapter = PaymentAdapter()
        binding.rlCards.adapter = paymentAdapter


        paymentAdapter.setItemClickListener { view, i, allCardResponseItem ->
            if (view.id == R.id.ivCard) {
                val editCardReqModel = EditCardReqModel(allCardResponseItem.id.toString())
                viewModel.editCard(editCardReqModel)
            }
            if (view.id == R.id.ivDelete) {
                val editCardReqModel = EditCardReqModel(allCardResponseItem.id.toString())
                viewModel.deleteCard(editCardReqModel)
            }
        }

    }
}