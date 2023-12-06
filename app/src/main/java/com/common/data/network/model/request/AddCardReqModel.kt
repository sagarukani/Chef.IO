package com.common.data.network.model.request

data class AddCardReqModel(
    val cardcvv: String,
    val cardexpiry: String,
    val cardname: String,
    val cardnumber: String
)