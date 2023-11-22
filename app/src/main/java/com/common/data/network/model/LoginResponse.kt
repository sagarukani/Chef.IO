package com.common.data.network.model

data class LoginResponse(
    var accessToken: String,
    var email: String,
    var id: Int,
    var roles: List<String>,
    var username: String
)