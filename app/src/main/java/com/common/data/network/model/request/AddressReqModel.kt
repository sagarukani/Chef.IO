package com.common.data.network.model.request

data class AddressReqModel(
    val city: String,
    val country: String,
    val gender: Int,
    val mobilenumber: Int,
    val postalcode: String,
    val profilepicture: String,
    val province: String,
    val street1: String,
    val street2: String
)