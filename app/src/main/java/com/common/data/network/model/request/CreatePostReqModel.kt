package com.common.data.network.model.request

data class CreatePostReqModel(
    val caption: String,
    val likecount: String,
    val media: String,
    val title: String
)