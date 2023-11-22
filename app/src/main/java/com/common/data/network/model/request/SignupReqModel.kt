package com.common.data.network.model.request

data class SignupReqModel(
    val email: String,
    val password: String,
    val roles: List<String>,
    val username: String
)