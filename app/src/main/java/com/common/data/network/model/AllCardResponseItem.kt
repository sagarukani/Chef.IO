package com.common.data.network.model

data class AllCardResponseItem(
    val cardcvv: String,
    val cardexpiry: String,
    val cardname: String,
    val cardnumber: String,
    val createdAt: String,
    val id: Int,
    val isprimary: Int,
    val updatedAt: String,
    val userid: Int
)