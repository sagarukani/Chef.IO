package com.common.data.network.model.request

data class AddressReqModel(
    var city: String,
    var country: String,
    var gender: Int,
    var mobilenumber: Int,
    var postalcode: String,
    var file: String,
    var province: String,
    var street1: String,
    var street2: String,
    var imagePath: String,
    var firstname: String,
    var lastname: String,
    var cuisine: String,
    var birthday: String = ""
)